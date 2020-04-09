package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = null;
        String server = "localhost:3306"; // MySQL 서버 주소
        String database = "board"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "hwangbo"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 드라이버 연결
        con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
        System.out.println("정상적으로 연결되었습니다.");

        return con;
    }

    // 드라이버 연결해제
    public void closeConnection(Connection con) throws SQLException {
        if (con != null)
            con.close();
    }
}
