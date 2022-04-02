package chess.dao;

import chess.piece.Piece;

import java.util.List;
import java.util.Map;

public interface PieceDao {
    void updatePosition(String position, String type, String team);

    List<Piece> findAllByBoardId(Long boardId);

}
