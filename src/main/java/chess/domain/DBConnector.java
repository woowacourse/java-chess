package chess.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String SERVER = "localhost";
    private static final String DATABASE = "chess_db";
    private static final String USER_NAME = "stopsilver";
    private static final String PASSWORD = "12345678";

    private DBConnector() {}

    private static class DBUtilsHolder {
        private static final DBConnector instance = new DBConnector();
    }

    public static DBConnector getInstance () {
        return DBUtilsHolder.instance;
    }

    public static Connection getConnection() {
        Connection con = null;
        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + "?useSSL=false", USER_NAME, PASSWORD);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public static void closeConnection(Connection con) {
        try { // 드라이버 연결해제
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }
}
