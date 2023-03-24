package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import repository.connector.JdbcConnector;

public class TestConnector implements JdbcConnector {
    private static final String SERVER = "localhost:3306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "dev_kong";
    private static final String PASSWORD = "qwer1234";

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
