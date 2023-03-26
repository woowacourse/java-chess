package chess.dao;

import chess.controller.ChessState;
import chess.domain.game.ChessGame;
import chess.domain.player.Player;
import chess.domain.room.ChessRoom;

public class ChessRoomDao {

    public static ChessRoom findByPlayer(final Player player) {
        final var query = "SELECT id, game_id, player_id, state FROM chess_room WHERE player_id = ? AND state != \"END\"";

        final RowMapper<ChessRoom> mapper = resultSet -> {
            if (resultSet.next()) {
                return ChessRoom.of(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)
                );
            }
            return null;
        };

        return JdbcTemplate.select(query, mapper, player.getId());
    }

    public static ChessRoom create(final ChessGame chessGame, final Player player) {
        final var query = "INSERT INTO chess_room(game_id, player_id) VALUES (?, ?)";

        JdbcTemplate.executeQuery(query, chessGame.getId(), player.getId());

        return findByPlayer(player);
    }

    public static void updateState(final ChessRoom chessRoom, final ChessState state) {
        final var query = "UPDATE chess_room SET state = ? WHERE id = ?";

        JdbcTemplate.executeQuery(query, state.getValue(), chessRoom.getId());
    }
}
