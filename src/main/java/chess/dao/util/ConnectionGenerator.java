package chess.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionGenerator {

    private ConnectionGenerator() {
    }

    public static PreparedStatement getStatement(final String sql) throws SQLException {
        final Connection connection = getConnection();
        return connection.prepareStatement(sql);
    }

    private static Connection getConnection() {
        final String url = "jdbc:mysql://localhost:3306/chess";
        final String userName = "root";
        final String password = "root";

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
