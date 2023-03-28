package common;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Supplier;

public final class JdbcContextImpl implements JdbcContext {

    private final JdbcConnection jdbcConnection;
    private Connection connection;

    public JdbcContextImpl(final JdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
        this.connection = jdbcConnection.getConnection();
    }

    @Override
    public <T> T workWithTransaction(final TransactionStrategy<T> transactionStrategy) {
        try {
            connection.setAutoCommit(false);
            return transactionStrategy.execute(connection);
        } catch (SQLException exception) {
            rollback(connection);
            throw new IllegalStateException(exception);
        }
    }

    @Override
    public <T> void makeTransactionUnit(final Supplier<T> supplier) {
        makeConnection();
        try {
            connection.setAutoCommit(false);
            supplier.get();
            connection.commit();
        } catch (SQLException exception) {
            rollback(connection);
            throw new IllegalStateException(exception);
        } finally {
            close(connection);
        }
    }

    private void makeConnection() {
        if (isClosed()) {
            connection = jdbcConnection.getConnection();
        }
    }

    private boolean isClosed() {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
