package chess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    public static Connection getConnection() {
        Connection con = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "chess"; // MySQL DATABASE 이름
        String userName = "root"; //  MySQL 서버 아이디
        String password = "201424415"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", userName, password);

            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            throw new IllegalArgumentException("드라이버 연결 오류");
        }

        return con;
    }

    // 드라이버 연결해제
    public static void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("드라이버 연결해제 오류");
        }
    }
}

