package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionSetUp {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/chess?useSSL=false&serverTimezone=UTC";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private DBConnectionSetUp() {
    }

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("DB 연결 오류 : " + e.getMessage());
        }

        try {
            con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("DB 연결이 정상적으로 이루어졌습니다");
        } catch (SQLException e) {
            System.err.println("DB 연결 오류 : " + e.getMessage());
        }
        return con;
    }
}
