package domain.dao;

import common.TransactionStrategy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestTransactionContext {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public <T> T workWithTransaction(final TransactionStrategy<T> transactionStrategy) {
        final Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            return transactionStrategy.execute(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            rollback(connection);
            close(connection);
        }
    }

    private static void rollback(final Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void close(final Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
