package chess.dao;

import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public interface BoardDao {
    void create(final Map<Position, Piece> board, final int gameId);

    Map<Position, Piece> findByLastGameBoard(final int gameId);

    void deleteAll(final int gameId);
}
