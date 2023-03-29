package chess.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTemplate {

    private final ConnectionPool connectionPool;

    public JdbcTemplate(final ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void executeUpdate(final String query, final Object... parameters) {
        final Connection connection = connectionPool.getConnection();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public <T> List<T> query(final String query, final RowMapper<T> rowMapper, final Object... parameters) {
        final Connection connection = connectionPool.getConnection();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public <T> Optional<T> queryForSingleResult(final String query, final RowMapper<T> rowMapper,
                                                final Object... parameters) {
        final Connection connection = connectionPool.getConnection();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(rowMapper.mapRow(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
