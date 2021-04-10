package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static final String SERVER = "localhost:3306";
    private static final String DATABASE = "chessdb";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
