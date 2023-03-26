package chess.dao;

import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {

    public static void executeQuery(final String query, final Object... parameters) {
        try (final var connection = DBConnection.get()) {
            final var preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int insertAndReturnKey(final String query, final Object... parameters) {
        try (final var connection = DBConnection.get()) {
            final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            preparedStatement.executeUpdate();

            final var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("id가 생성되지 않았습니다");
    }

    public static <T> T select(final String query, final RowMapper<T> rowMapper, final Object... parameters) {
        try (final var connection = DBConnection.get()) {
            final var preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            final var resultSet = preparedStatement.executeQuery();

            return rowMapper.mapRow(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
