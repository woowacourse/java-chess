package chess.dao;

import chess.piece.Piece;

import java.util.List;

public interface PieceDao {
    void updatePieceByPosition(final String type, final String team, final String position);

    List<Piece> findAllByBoardId(Long boardId);

    void save(List<Piece> pieces, Long boardId);
}
