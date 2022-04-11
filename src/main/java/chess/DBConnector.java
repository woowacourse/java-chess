package chess;

import java.sql.*;

public class DBConnector {

    private static final String URL = "jdbc:mysql://localhost:3307/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private DBConnector() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("데이터베이스 연결을 실패했습니다.");
    }
}
