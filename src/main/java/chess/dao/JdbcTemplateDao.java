package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface JdbcTemplateDao {
    default Connection getConnection() {
        Connection connection = null;
        final String server = "localhost:13306";
        final String database = "chess";
        final String option = "?useSSL=false&serverTimezone=UTC";
        final String username = "root";
        final String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("!! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, username,
                password);
        } catch (SQLException e) {
            System.err.println("연결 오류: " + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }
}
