package chess.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionGenerator {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION;
    private static Connection CONNECTION;

    static {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException("데이터베이스에 연결할 수 없습니다.");
        }
    }

    private ConnectionGenerator() {
    }

    public static Connection getConnection() {
        return CONNECTION;
    }
}
