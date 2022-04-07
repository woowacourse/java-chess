package chess.dao;

import chess.game.Position;
import chess.piece.Piece;
import java.util.Map;

public interface PieceDao {
    void save(Long boardId, String position, Piece piece);

    void saveAll(Long boardId, Map<Position, Piece> pieces);

    Map<Position, Piece> findAllByBoardId(Long boardId);

    void deleteByPositionAndBoardId(String position, Long boardId);
}
