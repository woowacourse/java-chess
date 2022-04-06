package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnectionSetup implements ConnectionSetup {

    private static final String URL = "jdbc:mysql://localhost:13306/chessTest";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";

    @Override
    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL + OPTION, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
