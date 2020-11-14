package models.dao;

import models.bean.Album;
import models.dao.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

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

    public Album getAlbumByAlbumIdAndUserId(int albumId, int userId) {
        String sql = String.format("SELECT * FROM `album` WHERE %s = ? AND %s = ?", ID_COL, USER_ID_COL);
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, albumId);
            statement.setInt(2, userId);

            ResultSet resultSet = statement.executeQuery();

            return parseResultSetToAlbum(resultSet).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public boolean updateNameByAlbumIdAndUserId(String name, int albumId, int userId) {
        String sql = String.format("UPDATE `album` SET %s = ? WHERE %s = ? AND %s = ?", NAME_COL, ID_COL, USER_ID_COL);
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, albumId);
            statement.setInt(3, userId);

            return statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }


    public boolean deleteAlbumsByAlbumIdsAndUserId(int[] albumIds, int userId) {
        try {
            StringJoiner joiner = new StringJoiner(",");
            Arrays.stream(albumIds).forEach(id -> joiner.add("" + id));

            String sql = String.format("DELETE FROM `album` WHERE %s IN (%s) AND %s = ?", ID_COL,joiner.toString(), USER_ID_COL);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            return statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
