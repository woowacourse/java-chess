package chess.domain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSetup {

    public static Connection getConnection() {
        Connection con = null;
        final String server = "localhost:3306"; // MySQL 서버 주소
        final String database = "chess"; // MySQL DATABASE 이름
        final String option = "?useSSL=false&serverTimezone=UTC";
        final String userName = "root"; //  MySQL 서버 아이디
        final String password = "zeratul1"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" +
                database + option, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    // 드라이버 연결해제
    public static void closeConnection(final Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
