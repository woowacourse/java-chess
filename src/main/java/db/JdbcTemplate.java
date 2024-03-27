package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    private final ConnectionManager connectionManager;

    public JdbcTemplate() {
        this.connectionManager = new ConnectionManager();
    }

    void add(final String query, final String... parameters) {
        execute(query, parameters);
    }

    public <T> List<T> find(final String query, final RowMapper<T> mapper, final String... parameters) {
        return executeAndGet(query, mapper, parameters);
    }

    public void delete(final String query, final String... parameters) {
        execute(query, parameters);
    }

    private void execute(final String query, final String[] parameters) {
        try (final var connection = connectionManager.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, parameters);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private <T> List<T> executeAndGet(final String query, final RowMapper<T> mapper, final String[] parameters) {
        try (final var connection = connectionManager.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, parameters);
            return resolveResult(mapper, preparedStatement);
        } catch (final SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void setParameters(final PreparedStatement preparedStatement, final String... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            preparedStatement.setString(i + 1, parameters[i]);
        }
    }

    private <T> List<T> resolveResult(final RowMapper<T> mapper, final PreparedStatement preparedStatement) {
        try (final ResultSet resultSet = preparedStatement.executeQuery()) {
            final List<T> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(mapper.mapRow(resultSet));
            }
            return results;
        } catch (final SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
