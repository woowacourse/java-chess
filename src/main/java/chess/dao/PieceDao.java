package chess.dao;

import chess.game.Position;
import chess.piece.Piece;
import java.util.Map;

public interface PieceDao {

    void saveAll(Long boardId, Map<Position, Piece> pieces);

    Map<Position, Piece> findAllByBoardId(Long boardId);

    void deleteByBoardId(Long boardId);
}
