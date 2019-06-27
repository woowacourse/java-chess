package chess.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChessJDBCDriverConnector {
    private static String server = "localhost";
    private static String database = "chess";
    private static String userName = "aaaa";
    private static String password = "aaaa";

    private ChessJDBCDriverConnector() {
    }

    public static Connection getConnection() {
        loadJdbcDriver();
        return connectJdbcDriver();
    }

    private static void loadJdbcDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DataAccessException(e);
        }
    }

    private static Connection connectJdbcDriver() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false&serverTimezone=UTC", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        return connection;
    }
}
