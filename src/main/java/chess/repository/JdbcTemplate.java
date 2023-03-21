package chess.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    private final ConnectionGenerator connectionGenerator;

    public JdbcTemplate(final ConnectionGenerator connectionGenerator) {
        this.connectionGenerator = connectionGenerator;
    }

    public void executeUpdate(final String query, final Object... parameters) {
        try (final Connection connection = connectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public <T> T query(final String query, final RowMapper<T> rowMapper) {
        try (final Connection connection = connectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            return rowMapper.mapRow(resultSet, resultSet.getRow());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
