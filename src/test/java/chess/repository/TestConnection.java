package chess.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "testchess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION;
    private static Connection CONNECTION;

    static {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseConnectionFailException();
        }
    }

    private TestConnection() {
    }

    public static Connection getConnection() {
        return CONNECTION;
    }
}
