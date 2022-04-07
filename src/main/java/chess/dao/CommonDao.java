package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonDao {

    public static final int FAILED = -1;

    private static final String URL = "jdbc:mysql://localhost:3308/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 실행할 수 없는 명령입니다.", e);
        }
    }

    static void UpdateOrDelete(final String sql, StatementMaker<PreparedStatement> statementConsumer) {
        try {
            final Connection connection = getConnection();
            final PreparedStatement statement = connection.prepareStatement(sql);
            statementConsumer.makeStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 실행할 수 없는 명령입니다.", e);
        }
    }

    static int findId(final String sql, final StatementMaker<PreparedStatement> statementMaker,
                      final String columnLabel) {
        try {
            final Connection connection = getConnection();
            final PreparedStatement statement = connection.prepareStatement(sql);
            statementMaker.makeStatement(statement);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return FAILED;
            }
            return resultSet.getInt(columnLabel);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 실행할 수 없는 명령입니다.", e);
        }
    }

}
