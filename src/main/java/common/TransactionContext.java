package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionContext {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE, USERNAME, PASSWORD);
        } catch (final SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }

    public <T> T workWithTransaction(final TransactionStrategy<T> transactionStrategy) {
        final Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            return transactionStrategy.execute(connection);
        } catch (SQLException exception) {
            rollback(connection);
            throw new IllegalStateException(exception);
        } finally {
            commit(connection);
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

    private void commit(final Connection connection) {
        try {
            connection.commit();
        } catch (SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }
}
