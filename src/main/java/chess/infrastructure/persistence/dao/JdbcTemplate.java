package chess.infrastructure.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static chess.infrastructure.persistence.JdbcConnectionUtil.connection;

public class JdbcTemplate {

    interface NoResultSql {
        void noResult(final Connection connection,
                      final PreparedStatement preparedStatement) throws SQLException;
    }

    public void execute(final String sql, final NoResultSql noResultSql) {
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            noResultSql.noResult(connection, preparedStatement);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long saveAndGetId(final String sql, final String... params) {
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setString(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
            throw new IllegalArgumentException("생성된 Key 가 없음 가져올 수 없음");
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

    public <T> Optional<T> findOne(final String query,
                                   final RowMapper<T> rowMapper,
                                   final String... params) {
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setString(i + 1, params[i]);
            }
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.ofNullable(rowMapper.mapRow(resultSet));
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> findAll(final String query,
                               final RowMapper<T> rowMapper,
                               final String... params) {
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setString(i + 1, params[i]);
            }
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(rowMapper.mapRow(resultSet));
            }
            return entities;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public interface RowMapper<T> {

        T mapRow(final ResultSet resultSet) throws SQLException;
    }
}
