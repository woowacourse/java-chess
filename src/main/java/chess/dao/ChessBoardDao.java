package chess.dao;

import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public interface ChessBoardDao {

    void save(long chessGameId, Map<Position, Piece> board);
    void update(Position piecePosition, Piece piece);
}
