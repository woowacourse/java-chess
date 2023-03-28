package common;

import common.connection.JdbcConnection;
import java.sql.Connection;
import java.sql.SQLException;

public final class JdbcContextImpl implements JdbcContext {

    private final JdbcConnection jdbcConnection;

    public JdbcContextImpl(final JdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    @Override
    public <T> T workWithTransaction(final TransactionStrategy<T> transactionStrategy) {
        final Connection connection = jdbcConnection.getConnection();
        try {
            connection.setAutoCommit(false);
            final T result = transactionStrategy.execute(connection);
            connection.commit();
            return result;
        } catch (SQLException exception) {
            rollback(connection);
            throw new IllegalStateException(exception);
        } finally {
            close(connection);
        }
    }

    private void rollback(final Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }

    private void close(final Connection connection) {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }
}
