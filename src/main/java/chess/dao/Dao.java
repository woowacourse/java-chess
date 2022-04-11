package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dao {

    private static final String MYSQL_HOST = "localhost:3306";
    private static final String MYSQL_DATABASE = "chess";
    private static final String MYSQL_USERNAME = "user";
    private static final String MYSQL_PASSWORD = "password";

    protected Connection getConnection() {
        loadDriver();
        return loadConnection();
    }

    private Connection loadConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + MYSQL_HOST + "/" + MYSQL_DATABASE,
                    MYSQL_USERNAME,
                    MYSQL_PASSWORD);
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
