package chess.infrastructure.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static chess.infrastructure.persistence.JdbcConnectionUtil.connection;

public class JdbcTemplate {

    interface NoResultSql {
        void noResult(final Connection connection, final PreparedStatement preparedStatement) throws SQLException;
    }

    interface FindOneQuery<T> {
        T singleResult(final Connection connection, final PreparedStatement preparedStatement) throws SQLException;
    }

    interface FindAllQuery<T> {
        List<T> results(final Connection connection, final PreparedStatement preparedStatement) throws SQLException;
    }

    public void execute(final String sql, final NoResultSql noResultSql) {
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            noResultSql.noResult(connection, preparedStatement);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Optional<T> findOne(final String query, final FindOneQuery<T> findOneQuery) {
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return Optional.ofNullable(findOneQuery.singleResult(connection, preparedStatement));
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> findAll(final String query, final FindAllQuery<T> findAllQuery) {
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return findAllQuery.results(connection, preparedStatement);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate(final String sql, final String... params) {
        execute(sql, (connection, preparedStatement) -> {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setString(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
        });
    }
}
