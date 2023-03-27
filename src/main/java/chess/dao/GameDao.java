package chess.dao;

import chess.domain.game.Game;
import chess.dto.GameDto;
import java.sql.SQLException;

public final class GameDao{
    private final DBConnection dbConnection = new DBConnection();

    public void save(Game game) {
        final var query = "INSERT INTO game(turn) VALUES(?)";
        try (var connection = dbConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            String turn = game.getTurn().name();
            preparedStatement.setString(1, turn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public GameDto select() {
        final var query = "SELECT * FROM game";
        try (var connection = dbConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new GameDto(resultSet.getInt("id"), resultSet.getString("turn"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        final var query = "DELETE FROM game";
        try (var connection = dbConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
