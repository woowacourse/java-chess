package dao;

import chess.ChessGame;
import chess.domain.piece.Color;
import java.util.List;

public interface ChessStatusDao {
    List<String> gameIds();

    Color selectTurn(final String gameId);

    void save(final ChessGame chessGame, final String gameId);

    String create(final ChessGame chessGame);

    void update(final ChessGame chessGame, final String gameId);
}
