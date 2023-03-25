package chess.dao.template;

import chess.dao.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcContext {
    private static final String DATABASE_CONNECTION_EXCEPTION_MESSAGE = "[ERROR] 데이터베이스의 연결에 문제가 발생했습니다.";

    public void update(String query) {
        workWithStatementStrategy(c -> c.prepareStatement(query));
    }

    public void insert(String query, Object... parameters) {
        workWithStatementStrategy(c -> {
            PreparedStatement ps = c.prepareStatement(query);
            for (int i = 1; i <= parameters.length; i++) {
                ps.setObject(i, parameters[i - 1]);
            }
            return ps;
        });
    }

    public <T> T select(String query, RowMapper<T> rowMapper) {
        return workWithStatementStrategy(c -> c.prepareStatement(query), rowMapper);
    }

    private void workWithStatementStrategy(StatementStrategy statementStrategy) {
        Connection c = ConnectionProvider.getConnection();
        try (PreparedStatement ps = statementStrategy.makePreparedStatement(c)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        } finally {
            ConnectionProvider.close(c);
        }
    }

    private <T> T workWithStatementStrategy(StatementStrategy statementStrategy, RowMapper<T> rowMapper) {
        Connection c = ConnectionProvider.getConnection();
        try (PreparedStatement ps = statementStrategy.makePreparedStatement(c);
             ResultSet resultSet = ps.executeQuery()) {
            return rowMapper.mapRow(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        } finally {
            ConnectionProvider.close(c);
        }
    }

    public void transaction(Runnable runnable) {
        ConnectionProvider.startTransaction();
        try {
            runnable.run();
        } catch (Exception e) {
            ConnectionProvider.rollback();
            throw e;
        } finally {
            ConnectionProvider.commit();
            ConnectionProvider.endTransaction();
        }
    }
}
