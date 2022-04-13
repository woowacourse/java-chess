package chess.dao.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectorImpl implements DbConnector {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadConnection();
        return createConnection();
    }

    private void loadConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
