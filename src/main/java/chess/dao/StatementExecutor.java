package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatementExecutor implements AutoCloseable {
    private static final int START_INDEX = 1;

    private final PreparedStatement statement;
    private final Connection connection;
    private int paramIndex = START_INDEX;

    public StatementExecutor(Connection connection, String sql) {
        try {
            this.connection = connection;
            this.statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new DaoException("커넥션과 SQL 생성에 문제가 생겼습니다.", e);
        }
    }

    public StatementExecutor setInt(int paramValue) {
        try {
            statement.setInt(paramIndex++, paramValue);
            return this;
        } catch (SQLException e) {
            throw new DaoException("쿼리에 파라미터 삽입에 실패했습니다", e);
        }
    }

    public StatementExecutor setString(String paramValue) {
        try {
            statement.setString(paramIndex++, paramValue);
            return this;
        } catch (SQLException e) {
            throw new DaoException("쿼리에 파라미터 삽입에 실패했습니다", e);
        }
    }

    public int executeUpdate() {
        try (StatementExecutor executor = this) {
            return executor.statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("업데이트를 실패했습니다.", e);
        }
    }

    public ResultSet executeQuery() {
        try (StatementExecutor executor = this) {
            return executor.statement.executeQuery();
        } catch (SQLException e) {
            throw new DaoException("조회를 실패했습니다.", e);
        }
    }

    @Override
    public void close() throws SQLException {
        statement.close();
        connection.close();
    }
}
