package chess.database;

import chess.dao.mapper.RowMapper;
import chess.database.properties.ChessProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public final class JdbcTemplate {
    private final ChessProperties chessProperties;

    public JdbcTemplate(final ChessProperties chessProperties) {
        this.chessProperties = chessProperties;
    }

    public <T> Optional<T> findOne(final String query,
                                   final RowMapper<T> rowMapper,
                                   final String... params) {
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setString(i + 1, params[i]);
            }

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.ofNullable(rowMapper.mapRow(resultSet));
        } catch (SQLException e) {
            System.out.println("SQL Exception! = " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Long executeUpdate(final String query,
                              final String... params) {
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setString(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();

            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("Update Exception! - generated key error.");
            }
            return generatedKeys.getLong(1);
        } catch (SQLException e) {
            System.out.println("SQL Exception! = " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(chessProperties.getUrl(),
                    chessProperties.getUserName(), chessProperties.getPassword());
        } catch (final SQLException e) {
            System.out.println("DB Connection Exception! = " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
