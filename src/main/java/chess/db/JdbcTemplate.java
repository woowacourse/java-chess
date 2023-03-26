package chess.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public <T> T query(final String query, final RowMapper<T> rowMapper, final Object... parameters) {
        final Connection connection = connectionPool.getConnection();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            final ResultSet resultSet = preparedStatement.executeQuery();
            return rowMapper.mapRow(resultSet);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
