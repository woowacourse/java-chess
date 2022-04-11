package chess.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:13306/chess";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private DatabaseUtil() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (Objects.isNull(connection)) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public static void validExecute(int execute) throws SQLException {
        if (execute == 0) {
            throw new SQLException("SQL문을 무사히 실행했으나 반영된 것이 없습니다.");
        }
    }

    public static void validExecute(int[] executes) throws SQLException {
        boolean match = Arrays.stream(executes)
                .anyMatch(it -> it == 0);

        if (match) {
            throw new SQLException("SQL문을 무사히 실행했으나 반영된 것이 없습니다.");
        }
    }
}
