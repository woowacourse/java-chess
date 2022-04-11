package chess.web.dao;

import chess.domain.board.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface PieceDao {
    void removeAll();

    void savePieces(Map<Position, Piece> board);

    void deletePiece(Position position);

    void updatePosition(Position originPosition, Position newPosition);

    Map<Position, Piece> findAll();
}
