package models.dao;

import models.bean.User;
import models.dao.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;
    private static final String ID_COL = "i_id";
    private static final String USERNAME_COL = "t_username";
    private static final String PASSWORD_COL = "t_password";
    private static final String NAME_COL = "t_name";
    private static UserDao userDao;

    public static UserDao getInstance(){
        if(userDao == null){
            userDao = new UserDao();
        }
        return userDao;
    }

    public UserDao(){
        connection = DBUtil.getConnection();
    }

    public User getUserByUsername(String username){
        String sql = "SELECT * FROM `user` WHERE " + USERNAME_COL +  " = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet =  statement.executeQuery();

            List<User> users = parseResultSetToUser(resultSet);
            if(users.size() > 0){
                return users.get(0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean addUser(User user){
        String sql = "INSERT INTO `user` VALUES (DEFAULT, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());

            return statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private List<User> parseResultSetToUser(ResultSet resultSet){
        List<User> users = new ArrayList<>();
        try {
            if(resultSet.next()){
                User user = new User(
                        resultSet.getInt(ID_COL),
                        resultSet.getString(USERNAME_COL),
                        resultSet.getString(PASSWORD_COL),
                        resultSet.getString(NAME_COL),
                        null
                );
                users.add(user);
            }
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
