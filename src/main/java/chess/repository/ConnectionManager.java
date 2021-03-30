package chess.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String SERVER = "localhost:13306";
    private static final String DB_NAME = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL_FORMAT = "jdbc:mysql://%s/%s%s";

    private ConnectionManager() {
    }

    public static Connection makeConnection() {
        loadDriver();
        return connectDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    private static Connection connectDriver() {
        try {
            String url = String.format(URL_FORMAT, SERVER, DB_NAME, OPTION);
            return DriverManager.getConnection(url, USER_NAME, PASSWORD);
        } catch (SQLException sqlException) {
            return null;
        }
    }
}
