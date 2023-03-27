package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;

public class ConnectionUtil {
    private static final String URL = "jdbc:mysql://localhost";
    private static final String PORT = "13306";
    private static final String DB = "chess";
    private static final String OPTION = "userSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONNECTION_FORM = MessageFormat.format("{0}:{1}/{2}?{3}", URL, PORT, DB, OPTION);

    private ConnectionUtil() {
    }

    public static Connection connection() {
        try {
            return DriverManager.getConnection(CONNECTION_FORM, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
