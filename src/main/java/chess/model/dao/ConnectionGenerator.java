package chess.model.dao;

import chess.model.exception.DataBaseCanNotConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionGenerator {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess-production";
    private static final String OPTION = "?useSSL=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            throw new DataBaseCanNotConnectException();
        }
    }
}
