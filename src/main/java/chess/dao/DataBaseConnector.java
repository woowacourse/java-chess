package chess.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBaseConnector {
    public static Connection getConnection() {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("src/main/resources/database.properties")) {
            properties.load(in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Connection con = null;
        String url = properties.getProperty("jdbc.url");
        String userName = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public static void closeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            throw new DataBaseConnectException("연결 해제 중에 에러가 발생하였습니다.");
        }
    }

    public static void closeConnection(Connection con, PreparedStatement pstmt) {
        closeConnection(con, pstmt, null);
    }
}
