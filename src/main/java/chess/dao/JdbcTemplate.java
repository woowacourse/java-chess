package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    private final Connection connection;

    public JdbcTemplate(final Connection connection) {
        this.connection = connection;
    }

    public void executeUpdate(final String query, final Object... parameters) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int index = 1; index <= parameters.length; index++) {
                preparedStatement.setObject(index, parameters[index - 1]);
            }
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object> executeQuery(final String query, final List<String> resultParameters,
                                    final Object... sqlParameters) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final List<Object> result = new ArrayList<>();
            for (int index = 1; index <= sqlParameters.length; index++) {
                preparedStatement.setObject(index, sqlParameters[index - 1]);
            }

            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                for (int i = 0; i < resultParameters.size(); i++) {
                    result.add(resultSet.getObject(resultParameters.get(i)));
                }
            }
            return result;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
