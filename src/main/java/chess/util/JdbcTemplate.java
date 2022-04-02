package chess.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {

    private static final String SQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/chess?autoReconnect=true";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(SQL_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection(String url) {
        Connection conn = null;
        try {
            Class.forName(SQL_DRIVER);
            conn = DriverManager.getConnection(url, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
