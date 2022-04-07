package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        loadDriver();
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("mysql 연결 실패");
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
