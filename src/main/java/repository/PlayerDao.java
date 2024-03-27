package repository;

import domain.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class PlayerDao {

    private final Connection connection;

    public PlayerDao(final Connection connection) {
        this.connection = connection;
    }

    public int add(final Player player) {
        final var query = "INSERT INTO player VALUES(null, ?)";
        try (final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, player.getName());

            preparedStatement.executeUpdate();

            try (final ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return (int) generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating game failed, no ID obtained.");
                }
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<String> findNameById(final int id) {
        final var query = "SELECT name FROM player WHERE id = (?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(resultSet.getString("name"));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
