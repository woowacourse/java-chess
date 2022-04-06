package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonDao {

    private static final String URL = "jdbc:mysql://localhost:3308/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    static void UpdateOrDelete(final String sql, StatementMaker<PreparedStatement> statementConsumer) {
        final Connection connection = getConnection();
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statementConsumer.makeStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static int findId(final String sql, final StatementMaker<PreparedStatement> statementMaker,
                      final String columnLabel) {
        final Connection connection = getConnection();
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statementMaker.makeStatement(statement);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return -1;
            }
            return resultSet.getInt(columnLabel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
