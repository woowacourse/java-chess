package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionGenerator {

    private ConnectionGenerator() {
    }

    static Connection getConnection() {
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
