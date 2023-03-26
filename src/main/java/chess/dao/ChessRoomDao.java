package chess.dao;

import chess.controller.ChessState;
import chess.domain.game.ChessGame;
import chess.domain.player.Player;
import chess.domain.room.ChessRoom;

import java.sql.SQLException;

public class ChessRoomDao {

    public static ChessRoom findByPlayer(final Player player) {
        final var query = "SELECT id, game_id, player_id, state FROM chess_room WHERE player_id = ? AND state != \"END\"";
        try (final var connection = DBConnection.get()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getId());

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return ChessRoom.of(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ChessRoom create(final ChessGame chessGame, final Player player) {
        final var query = "INSERT INTO chess_room(game_id, player_id) VALUES (?, ?)";

        JdbcTemplate.executeUpdate(query, chessGame.getId(), player.getId());

        return findByPlayer(player);
    }

    public static void updateState(final ChessRoom chessRoom, final ChessState state) {
        final var query = "UPDATE chess_room SET state = ? WHERE id = ?";

        JdbcTemplate.executeUpdate(query, state.getValue(), chessRoom.getId());
    }
}
