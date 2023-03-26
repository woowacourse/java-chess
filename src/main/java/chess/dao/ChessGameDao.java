package chess.dao;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.room.ChessRoom;
import chess.view.TeamName;

public class ChessGameDao {

    public static ChessGame create(final Board board) {
        final var query = "INSERT INTO chess_game(board_id) VALUES (?)";

        final int id = JdbcTemplate.insertAndReturnKey(query, board.getId());

        return ChessGame.of(id, board.getId());
    }

    public static ChessGame findById(final ChessRoom chessRoom) {
        final var query = "SELECT * FROM chess_game WHERE id = ?";

        final RowMapper<ChessGame> mapper = resultSet -> {
            if (resultSet.next()) {
                return ChessGame.of(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3)
                );
            }
            return null;
        };

        return JdbcTemplate.select(query, mapper, chessRoom.getGameId());
    }

    public static void updateTurn(final ChessGame chessGame) {
        final var query = "UPDATE chess_game SET turn = ? WHERE id = ?";
        JdbcTemplate.executeQuery(query, TeamName.findByTeam(chessGame.getTurn()), chessGame.getId());
    }
}
