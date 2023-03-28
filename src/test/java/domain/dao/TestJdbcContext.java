package domain.dao;

import common.JdbcContext;
import common.TransactionStrategy;
import common.connection.JdbcConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class TestJdbcContext implements JdbcContext {

    private final JdbcConnection jdbcConnection;
    private Connection connection;

    public TestJdbcContext(final JdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
        this.connection = jdbcConnection.getConnection();
    }

    public <T> T workWithTransaction(final TransactionStrategy<T> transactionStrategy) {
        try {
            connection.setAutoCommit(false);
            return transactionStrategy.execute(connection);
        } catch (SQLException e) {
            rollback(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> void makeTransactionUnit(final TransactionStrategy<T> transactionStrategy) {
        makeConnection();
        try {
            connection.setTransactionIsolation(connection.TRANSACTION_READ_UNCOMMITTED);
            connection.setAutoCommit(false);
            final Savepoint savepoint = connection.setSavepoint();
            transactionStrategy.execute(connection);
            connection.rollback(savepoint);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
