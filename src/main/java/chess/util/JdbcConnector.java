package chess.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private JdbcConnector() {
    }

    public static Connection getConnection() {
        loadDriver(); // 드라이브를 등록하기 위한 동작
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static Connection loadDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
