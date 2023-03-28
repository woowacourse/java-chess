package chess.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTemplate implements Transaction {
    private final Connection connection;

    public JdbcTemplate(final Connection connection) {
        this.connection = connection;
    }

    public void batchTransaction(Runnable... transactions) {
        try {
            connection.setAutoCommit(false);

            for (Runnable transaction : transactions) {
                transaction.run();
            }

            connection.commit();
        } catch (final SQLException e) {
            rollback(connection);
            throw new RuntimeException(e);
        } finally {
            setAutoCommitTrue(connection);
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void setAutoCommitTrue(Connection connection) {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
