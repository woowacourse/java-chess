package chess.model.dao;

import chess.model.exception.QueryFailException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplateImpl implements JdbcTemplate {

    @Override
    public void executeUpdate(final String query, final Object... parameters) {
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new QueryFailException();
        }
    }

    @Override
    public <T> T executeUpdate(final String query, final RowMapper<T> rowMapper, final Object... parameters) {
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            preparedStatement.executeUpdate();
            final ResultSet resultSet = preparedStatement.getGeneratedKeys();
            return rowMapper.mapRow(resultSet);
        } catch (final SQLException e) {
            throw new QueryFailException();
        }
    }

    @Override
    public <T> T executeQuery(final String query, final RowMapper<T> rowMapper, final Object... parameters) {
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            final ResultSet resultSet = preparedStatement.executeQuery();
            return rowMapper.mapRow(resultSet);
        } catch (final SQLException e) {
            throw new QueryFailException();
        }
    }
}
