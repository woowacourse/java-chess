package dao;

import chess.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface ChessGameDao {

    void save(ChessGame chessGame, final String gameId);

    Map<Position, Piece> select(final String gameId);

    void reset(final String gameId);
}
