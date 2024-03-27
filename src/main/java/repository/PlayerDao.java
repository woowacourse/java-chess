package repository;

import domain.player.Player;
import domain.player.PlayerName;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class PlayerDao {

    private final Connection connection;

    public PlayerDao(final Connection connection) {
        this.connection = connection;
    }

    public Player add(final PlayerName name) {
        final var query = "INSERT INTO player VALUES(null, ?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name.getName());

            preparedStatement.executeUpdate();

            return new Player(name);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<String> findNameById(final int id) {
        final var query = "SELECT name FROM player WHERE id = ?";
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

    public boolean exist(final PlayerName name) {
        final var query = "SELECT * FROM player WHERE name = ?";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name.getName());
            final var resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(final PlayerName pobi) {
        final var query = "DELETE FROM player where name = ?";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pobi.getName());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
