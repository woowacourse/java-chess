package domain.dao;

import common.JdbcContext;
import common.TransactionStrategy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class TestJdbcContext implements JdbcContext {

    private Connection connection;

    public <T> T workWithTransaction(final TransactionStrategy<T> transactionStrategy) {
        try {
            connection.setAutoCommit(false);
            return transactionStrategy.execute(connection);
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException(e);
        }
    }

    public <T> void testWithRollback(final TransactionStrategy<T> transactionStrategy) throws SQLException {
        try {
            connection.setTransactionIsolation(connection.TRANSACTION_READ_UNCOMMITTED);
            connection.setAutoCommit(false);
            final Savepoint savepoint = connection.setSavepoint();
            transactionStrategy.execute(connection);
            connection.rollback(savepoint);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.rollback();
            connection.close();
        }
    }

    private void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setConnection(final Connection connection) {
        this.connection = connection;
    }
}
