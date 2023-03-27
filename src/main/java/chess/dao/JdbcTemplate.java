package chess.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {
    private final Connection connection;

    public JdbcTemplate() {
        this.connection = Connector.getConnection();
    }


    public int save(final String query, final Object... parameters) {
        try (final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int generatedId = 1;
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
            return generatedId;
        } catch (final SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void executeUpdate(final String query, final Object... parameters) {
        try (final var preparedStatement = connection.prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public <T> T executeQuery(final String query, final RowMapper<T> rowMapper, final Object... parameters) {
        try (final var preparedStatement = connection.prepareStatement(query)) {
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
