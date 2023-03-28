package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class JdbcConnection {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            throw new RuntimeException("DB 연결 오류", e);
        }
    }
}
