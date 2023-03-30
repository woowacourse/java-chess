package chess.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcTransactionConnector<T> implements JdbcConnector<T> {

    @Override
    public void executeUpdate(String query, List<String> parameters) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            for (int i = 1; i < parameters.size() + 1; i++) {
                preparedStatement.setString(i, parameters.get(i - 1));
            }
            preparedStatement.executeUpdate();
            commit(connection);
        } catch (Exception exception) {
            rollback(connection);
            throw new IllegalStateException(exception);
        } finally {
            release(connection);
        }
    }

    @Override
    public T executeQuery(String query, RowMapper rowMapper) {
        throw new IllegalStateException("[ERROR] WRITE 작업만 가능합니다.");
    }

    @Override
    public T executeQuery(String query, List<String> parameters, RowMapper rowMapper) {
        throw new IllegalStateException("[ERROR] WRITE 작업만 가능합니다.");
    }

    private void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }

    private void release(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }
}
