package chess.web.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static String URL = "jdbc:mysql://localhost:3306/chess";
    private static String USER = "user";
    private static String PASSWORD = "password";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            setConnection();
        }
        return connection;
    }

    private static void setConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
