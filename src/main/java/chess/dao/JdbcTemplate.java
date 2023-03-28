package chess.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTemplate {
    private static final Connection connection = ConnectionGenerator.getConnection();

    private JdbcTemplate() {
    }

    public static void batchTransaction(Runnable... transactions) {
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


    public static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void setAutoCommitTrue(Connection connection) {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
