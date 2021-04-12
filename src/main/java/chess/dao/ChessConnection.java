package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChessConnection {
    private static final String server = "localhost:13306";
    private static final String database = "chess";
    private static final String option = "?useSSL=false&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String password = "root";

    public static Connection getConnection() {
        checkDriver();
        return connect();
    }

    private static void checkDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}