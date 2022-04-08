package chess.domain.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public interface PieceDao {
    void save(Map<Position, Piece> board);

    Map<Position, Piece> load();

    boolean existPieces();

    void delete();

    void updatePosition(String source, String target);
}
