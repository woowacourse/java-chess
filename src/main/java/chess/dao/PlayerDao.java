package chess.dao;

import chess.domain.player.Player;

import java.sql.SQLException;

public class PlayerDao {

    public static Player findByName(final String name) {
        final var query = "SELECT * FROM player WHERE name = ?";
        try (final var connection = DBConnection.get()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Player.of(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Player create(final String name) {
        final var query = "INSERT INTO player(name) VALUES (?)";
        try (final var connection = DBConnection.get()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            return findByName(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
