package chess.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcContext {

    private final DataSource dataSource = new DataSource();

    public void executeUpdate(StatementStrategy statementStrategy) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = statementStrategy.makePreparedStatement(connection)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("sql 실행 실패", e.getCause());
        }
    }

    public void executeBatch(StatementStrategy statementStrategy) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = statementStrategy.makePreparedStatement(connection)) {
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new IllegalStateException("sql 실행 실패", e.getCause());
        }
    }

    public <T> T queryForObject(StatementStrategy statementStrategy, RowMapper<T> rowMapper) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = statementStrategy.makePreparedStatement(connection)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return rowMapper.mapRow(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException("sql 실행 실패", e.getCause());
        }
    }
}
