package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplate {

    private static final String WRONG_QUERY_ERROR_MESSAGE_FORMAT = "DB 쿼리 오류: %s";
    private final ConnectionManager connectionManager = new ConnectionManager();

    public <T> void executeUpdate(String query, List<Object> parameters) {
        try (final Connection connection = connectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, parameters);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(String.format(WRONG_QUERY_ERROR_MESSAGE_FORMAT, query), exception.getCause());
        }
    }

    public <T> T executeQuery(String query, List<Object> parameters, RowMapper<T> rowMapper) {
        try (final Connection connection = connectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, parameters);
            ResultSet resultSet = preparedStatement.executeQuery();
            return rowMapper.mapRow(resultSet);
        } catch (SQLException exception) {
            throw new RuntimeException(String.format(WRONG_QUERY_ERROR_MESSAGE_FORMAT, query), exception.getCause());
        }
    }

    private <T> void setParameters(PreparedStatement preparedStatement, List<Object> parameters) throws SQLException {
        for (int index = 1; index <= parameters.size(); index++) {
            preparedStatement.setString(index, String.valueOf(parameters.get(index - 1)));
        }
    }

}
