package chess.db;

import chess.util.SqlConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        roadDriver();
        return createConnection();
    }

    private static void roadDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (final Exception e) {
            e.printStackTrace();
            throw new SqlConnectionException("Driver 로딩을 실패했습니다.", e);
        }
    }

    private static Connection createConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SqlConnectionException("Connection 연결에 실패했습니다.", e);
        }
    }
}
