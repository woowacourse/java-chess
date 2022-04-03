package chess.dao;

import chess.piece.Piece;

import java.util.List;

public interface PieceDao {
    void updatePosition(final String position, final String type, final String team);

    List<Piece> findAllByBoardId(Long boardId);

    Long findIdByPositionAndBoardId(String position, Long boardId);
}
