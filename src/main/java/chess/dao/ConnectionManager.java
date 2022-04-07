package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String MYSQL_CJ_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public <T> T executeQuery(ConnectionMapper<T> connectionMapper) {
        try (final Connection connection = getConnection()) {
            return connectionMapper.execute(connection);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName(MYSQL_CJ_JDBC_DRIVER);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
