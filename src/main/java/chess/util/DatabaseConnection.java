package chess.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final static String SERVER = "localhost";
    private final static String DATABASE = "chess";
    private final static String USER_NAME = "user";
    private final static String PASSWORD = "1234";

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            String url = "jdbc:mysql://" + SERVER + "/" + DATABASE + "?serverTimezone=UTC";
            con = DriverManager.getConnection(url, USER_NAME, PASSWORD);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }
}