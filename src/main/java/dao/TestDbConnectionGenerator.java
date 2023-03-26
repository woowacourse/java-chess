package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDbConnectionGenerator implements ConnectionGenerator {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    @Override
    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
