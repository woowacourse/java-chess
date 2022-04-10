package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String CONNECTION_FAILED_EXCEPTION_MESSAGE = "데이터베이스 접속에 실패하였습니다.";

    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException(CONNECTION_FAILED_EXCEPTION_MESSAGE);
        }
    }

    private ConnectionManager() {
    }

    public static Connection getConnection() {
        return connection;
    }
}
