package models.dao;

import models.bean.Album;
import models.bean.Image;
import models.dao.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageDao {
    private Connection connection;
    private static final String ID_COL = "i_id";
    private static final String NAME_COL = "t_name";
    private static final String PATH_COL = "t_path";
    private static final String PATH_THUMBNAIL_COL = "t_path_thumbnail";
    private static final String USER_ID_COL = "i_user";
    private static final String ALBUM_ID_COL = "i_album";

    private static ImageDao imageDao;

    public static ImageDao getInstance() {
        if (imageDao == null) {
            imageDao = new ImageDao();
        }
        return imageDao;
    }

    public ImageDao() {
        connection = DBUtil.getConnection();
    }

    public List<Image> getAllImageByUserId(int userId) {
        String sql = "SELECT * FROM `image` WHERE " + USER_ID_COL + " = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            return parseResultSetToAlbum(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Image getImageByPathOrPathThumbnailAndUserId(String path, int userId) {
        String sql = String.format("SELECT * FROM `image` WHERE %s = ? AND ( %s = ? OR %s = ? )"
                                        , USER_ID_COL, PATH_COL, PATH_THUMBNAIL_COL);
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, path);
            statement.setString(3, path);
            ResultSet resultSet = statement.executeQuery();

            return parseResultSetToAlbum(resultSet).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean addImage(Image image) {
        String sql = "INSERT INTO `image` VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, image.getName());
            statement.setString(2, image.getPath());
            statement.setString(3, image.getPath());
            statement.setInt(4, image.getUserId());
            if (image.getAlbumId() <= 0) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, image.getAlbumId());
            }

            return statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private List<Image> parseResultSetToAlbum(ResultSet resultSet) {
        List<Image> images = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Image image = new Image(
                        resultSet.getInt(ID_COL),
                        resultSet.getString(NAME_COL),
                        resultSet.getString(PATH_COL),
                        resultSet.getString(PATH_THUMBNAIL_COL),
                        resultSet.getInt(USER_ID_COL),
                        (resultSet.getObject(ALBUM_ID_COL) == null) ? 0 : resultSet.getInt(ALBUM_ID_COL)
                );
                images.add(image);
            }
            return images;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
