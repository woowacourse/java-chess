package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static final String server = "localhost";
    private static final String database = "chess";
    private static final String userName = "root";
    private static final String password = "fantasy7";

    private static Connection connection;

    public static Connection start() throws SQLException {
        if (connection == null) {
            connection = connect();
        }
        return connection;
    }

    private static Connection connect() throws SQLException {
        loadDriver();
        return DriverManager.getConnection("jdbc:mysql://" + server + "/" + database
                + "?useSSL=false&serverTimezone=UTC", userName, password);
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void end() {
        if (connection != null) {
            closeConnection(connection);
        }
    }

    private static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
