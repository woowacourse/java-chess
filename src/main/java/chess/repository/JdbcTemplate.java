package chess.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    private final Connection connection;

    public JdbcTemplate(final Connection connection) {
        this.connection = connection;
    }

    public void executeUpdate(final String query, final Object... parameters) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalArgumentException("입력이 올바르지 않습니다.");
        }
    }

    public <T> T query(final String query, final RowMapper<T> rowMapper) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            return rowMapper.mapRow(resultSet);
        } catch (SQLException e) {
            throw new IllegalArgumentException("입력이 올바르지 않습니다.");
        }
    }
}
