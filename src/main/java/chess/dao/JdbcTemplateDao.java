package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface JdbcTemplateDao {

    default Connection getConnection() {
        Connection connection = null;
        String driverName = "jdbc:mysql://"; // jdbc 드라이버
        String server = "localhost:13306/"; // MySQL 서버 주소
        String database = "chess_game"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호
        String DELIMITER = ""; // 구분자

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            connection = DriverManager.getConnection(String.join(DELIMITER, driverName, server, database, option),
                userName,
                password);
            System.out.println();
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    // 드라이버 연결해제
    default void closeConnection(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println("connection 오류:" + e.getMessage());
        }
    }
}
