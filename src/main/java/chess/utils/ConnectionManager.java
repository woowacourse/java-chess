package chess.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String DATABASE_NAME = "chess";
    private static final String SERVER_URL = "localhost:13306";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + SERVER_URL + "/" + DATABASE_NAME + OPTION, USERNAME, PASSWORD);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }
}
