package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplate {

    private final ConnectionManager connectionManager = new ConnectionManager();

    public <T> void executeUpdate(String query, List<Object> parameters) {
        try (final Connection connection = connectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, parameters);
            preparedStatement.executeUpdate();
            // TODO SQL 예외 무엇으로 던지고, 어디서 어떻게 처리할 것인지?
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public <T> T executeQuery(String sql, List<Object> parameters, RowMapper<T> rowMapper) {
        try (final Connection connection = connectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setParameters(preparedStatement, parameters);
            ResultSet resultSet = preparedStatement.executeQuery();
            return rowMapper.mapRow(resultSet);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private <T> void setParameters(PreparedStatement preparedStatement, List<Object> parameters) throws SQLException {
        for (int index = 1; index <= parameters.size(); index++) {
            preparedStatement.setString(index, String.valueOf(parameters.get(index - 1)));
        }
    }

}
