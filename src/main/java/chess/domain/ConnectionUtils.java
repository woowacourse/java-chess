package chess.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL =
            "jdbc:mysql://localhost:3306/chess?serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String USER_NAME = "seed";
    private static final String PASSWORD = "asdqwe1!";

    private ConnectionUtils() {}

    public static Connection getConnection() {
        loadDriver();
        return connectDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection connectDriver() {
        try {
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
