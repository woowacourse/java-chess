package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnector {

    private static final String URL = "jdbc:mysql://localhost:3307/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final Logger logger = LoggerFactory.getLogger(DBConnector.class);

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return connection;
    }
}
