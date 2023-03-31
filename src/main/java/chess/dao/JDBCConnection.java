package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection implements DBConnection {

    private static final String DEFAULT_SERVER = "jdbc:mysql://localhost:3306/chess?useSSL=false&serverTimezone=UTC";
    private static final String DEFAULT_USERNAME = "root";
    private static final String DEFAULT_PASSWORD = "";

    private Connection connection;
    private final String server;
    private final String username;
    private final String password;

    public JDBCConnection() {
        this(DEFAULT_SERVER, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public JDBCConnection(String server, String username, String password) {
        this.server = server;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(server, username, password);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }
}
