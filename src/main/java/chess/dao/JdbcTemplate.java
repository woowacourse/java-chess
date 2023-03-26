package chess.dao;

import java.sql.SQLException;

public class JdbcTemplate {

    public static void executeQuery(final String query, final Object... parameters) {
        try (final var connection = DBConnection.get()) {
            final var preparedStatement = connection.prepareStatement(query);

            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
