package models.dao;

import models.bean.Album;
import models.dao.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDao {
    private Connection connection;
    private static final String ID_COL = "i_id";
    private static final String NAME_COL = "t_name";
    private static final String USER_ID_COL = "i_user";

    private static AlbumDao albumDao;

    public static AlbumDao getInstance(){
        if(albumDao == null){
            albumDao = new AlbumDao();
        }
        return albumDao;
    }

    public AlbumDao() {
        connection = DBUtil.getConnection();
    }

    public List<Album> getAllAlbumByUserId(int userId) {
        String sql = "SELECT * FROM `album` WHERE " + USER_ID_COL + " = ?" ;
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

    public boolean addAlbum(Album album) {
        String sql = "INSERT INTO `album` VALUES (DEFAULT, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, album.getName());
            statement.setInt(2, album.getUserId());

            return statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private List<Album> parseResultSetToAlbum(ResultSet resultSet) {
        List<Album> albums = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Album album = new Album(
                        resultSet.getInt(ID_COL),
                        resultSet.getString(NAME_COL),
                        resultSet.getInt(USER_ID_COL),
                        null
                );
                albums.add(album);
            }
            return albums;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
