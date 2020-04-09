package chess.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {
    public static Connection getConnection() {
        Connection connection = null;
        final String server = "localhost:13306";
        final String database = "WOOWA";
        final String option = "?useSSL=false&serverTimezone=UTC";
        final String userName = "root";
        final String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //어떤 드라이버에 연결할지
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver load 오류:" + e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
