package chess.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnectionPool implements ConnectionPool {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "testchess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION;

    private final Connection CONNECTION;

    public TestConnectionPool() {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseConnectionFailException();
        }
    }

    @Override
    public Connection getConnection() {
        return CONNECTION;
    }

    public void closeConnection() {
        try {
            CONNECTION.close();
        } catch (SQLException e) {
            throw new DatabaseConnectionFailException();
        }
    }
}
