package chess.domain.dao;

import chess.domain.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static final String PROD_DB_URL = "jdbc:mysql://localhost:13306/chess";
    public static final String DEV_DB_URL = "jdbc:mysql://localhost:13306/dev_chess";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Connection makeConnection(String url) {
        loadDriver();
        try {
            return DriverManager.getConnection(url, USER, PASSWORD);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
