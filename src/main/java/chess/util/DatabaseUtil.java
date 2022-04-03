package chess.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private DatabaseUtil() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException("데이터베이스 접속에 실패하였습니다.");
        }
    }

    public static ResultSet getQueryResult(String sql, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    }

    public static void executeCommand(String sql) {
        try (final Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("데이터 변경 작업에 실패하였습니다.");
        }
    }

    public static int getCountResult(String sql) {
        try (final Connection connection = getConnection()) {
            final ResultSet resultSet = getQueryResult(sql, connection);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("데이터 계산 작업에 실패하였습니다.");
        }
    }
}
