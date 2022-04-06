package chess.domain.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public interface PieceDao {
    void save(Map<Position, Piece> board, int boardId);

    Map<Position, Piece> load();

    boolean isExistsPieces();

    void deleteByBoardId(int boardId);

    void updatePosition(String source, String target, int boardId);
}
