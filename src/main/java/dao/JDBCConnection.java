package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    private static final String MYSQL_SERVER_ADDRESS = "localhost:3306";
    private static final String DB_NAME = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String SERVER_ID = "root";
    private static final String SERVER_PASSWORD = "root";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + MYSQL_SERVER_ADDRESS + "/" + DB_NAME + OPTION, SERVER_ID, SERVER_PASSWORD);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
        }

        return con;
    }
}
