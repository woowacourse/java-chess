package chess.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcReadOnlyConnector<T> implements JdbcConnector<T> {

    @Override
    public void executeUpdate(String query, List<String> parameters) {
        throw new IllegalStateException("[ERROR] READ-ONLY 작업만 가능합니다.");
    }

    @Override
    public T executeQuery(String query, RowMapper rowMapper) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery(query);
            return (T) rowMapper.convertToRow(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T executeQuery(String query, List<String> parameters, RowMapper rowMapper) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 1; i < parameters.size() + 1; i++) {
                preparedStatement.setString(i, parameters.get(i - 1));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            return (T) rowMapper.convertToRow(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
