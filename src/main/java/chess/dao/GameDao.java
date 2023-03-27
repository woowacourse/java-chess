package chess.dao;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Pieces;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.HashMap;
import java.util.Map;

public class GameDao {

    private final JdbcTemplate jdbcTemplate;

    public GameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveGame(final ChessGame game) {
        final String query = "INSERT INTO game (team, board) VALUES (?, ?)";
        return jdbcTemplate.save(query, game.getTeam().name(), game.getBoard().toString());
    }

    public void deleteGameById(final int gameId) {
        final var query = "DELETE FROM game WHERE game_id = ?";
        jdbcTemplate.executeUpdate(query, gameId);
    }


    public void updateGameById(ChessGame chessGame, final int gameId) {
        final var query = "UPDATE game SET team = ?, board = ? WHERE game_id = ?";
        jdbcTemplate.executeUpdate(query, chessGame.getTeam().toString(), chessGame.getBoard().toString(), gameId);
    }


    public ChessGame findGameById(final int gameId) {
        final var query = "SELECT team, board FROM game WHERE game_id = ?";
        return jdbcTemplate.executeQuery(query, resultSet -> {
            if (resultSet.next()) {
                return new ChessGame(
                        createBoard(resultSet.getString("board")),
                        Team.valueOf(resultSet.getString("team"))
                );
            }

            return null;
        }, gameId);
    }

    private Board createBoard(String input) {
        Map<Square, Piece> result = new HashMap<>();
        for (String pieces : input.split(",")) {
            Square square = Square.of(File.valueOf(pieces.split(":")[0].split(" ")[0]),
                    Rank.valueOf(pieces.split(":")[0].split(" ")[1]));

            Piece piece = Pieces.of(Team.valueOf(pieces.split(":")[1].split(" ")[0]),
                    PieceType.valueOf(pieces.split(":")[1].split(" ")[1]));

            result.put(square, piece);
        }
        return new Board(result);
    }
}
