package chess.model.dao;

import chess.model.exception.DataBaseCanNotConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnectionGenerator {

    private static final String SERVER = "localhost:13307";
    private static final String DATABASE = "chess-test";
    private static final String OPTION = "?useSSL=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            final Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        } catch (final SQLException e) {
            throw new DataBaseCanNotConnectException();
        }
    }
}
