package chess.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector implements Connector {
    private static final String serverAddress = "localhost:3306";
    private static final String databaseName = "chess";
    private static final String option = "?useSSL=false&serverTimezone=UTC";
    private static final String userName = "chess";
    private static final String password = "chess";

    private Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("!! JDBC Driver load 오류: " + e.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://" + serverAddress + "/" + databaseName + option,
                userName, password);
            System.out.println("DB에 정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.out.println("DB 연결 오류: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void closeConnection() {
        if (connection == null) {
            throw new IllegalArgumentException("null connection 을 닫을 수 없습니다.");
        }
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }
}
