package chess.model.repository.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChessMySqlConnector {

    public static Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "Chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager
                .getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
                    password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
