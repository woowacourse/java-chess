package chess.dao;

import java.sql.SQLException;

public class JdbcTemplate {

    public void executeUpdate(final String query, final Object... parameters) {
        try (final var preparedStatement = ConnectionGenerator.getConnection().prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public <T> T executeQuery(final String query, final RowMapper<T> rowMapper, final Object... parameters) {
        try (final var preparedStatement = ConnectionGenerator.getConnection().prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            final var resultSet = preparedStatement.executeQuery();
            return rowMapper.mapRow(resultSet);
        } catch (final SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
