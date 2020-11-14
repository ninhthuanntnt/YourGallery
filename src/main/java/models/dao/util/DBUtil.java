package models.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static String dbURL = "jdbc:mysql://localhost:3306/your_gallery_db?characterEncoding=UTF-8";
    private static String dbUsername = "root";
    private static String dbPassword = "Thuanthuc123";
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}
