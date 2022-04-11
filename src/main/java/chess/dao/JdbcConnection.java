package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static final Connection CONNECTION = initConnection();

    private static Connection initConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] JDBC 연결 오류");
        }
    }

    static Connection getConnection() {
        return CONNECTION;
    }
}
