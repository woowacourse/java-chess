package chess.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String URL = "jdbc:mysql://localhost:3310/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String ERROR_DB_CONNECTION_FAILED = "[ERROR] DB 연결에 실패했습니다.";
    private static final String ERROR_CLASS_NOT_FOUND = "[ERROR] 해당 클래스를 찾을 수 없습니다.";

    public static Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(ERROR_DB_CONNECTION_FAILED);
        }
        checkConnectionNotNull(connection);
        return connection;
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(ERROR_CLASS_NOT_FOUND);
        }
    }

    private static void checkConnectionNotNull(Connection connection) {
        if (connection == null) {
            throw new RuntimeException(ERROR_DB_CONNECTION_FAILED);
        }
    }

}
