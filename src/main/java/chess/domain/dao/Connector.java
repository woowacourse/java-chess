package chess.domain.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public Connection makeConnection() {
        loadDriver();
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
