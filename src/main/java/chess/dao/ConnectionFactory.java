package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        String server = "localhost:3306";
        String database = "chessBoard";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "1234";
        Connection con;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ConnectionException(String.format("!! JDBC Driver load 오류: %s", e.getMessage()));
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            throw new SQLExecuteException(String.format("연결 오류: %s", e.getMessage()));
        }

        return con;
    }
}
