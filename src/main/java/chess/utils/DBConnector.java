package chess.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (final Exception e) {
            System.out.println("커넥션 연결에 실패하였습니다.");
            e.printStackTrace();
        }
        return conn;
    }
}
