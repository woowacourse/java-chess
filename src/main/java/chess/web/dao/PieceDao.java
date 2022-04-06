package chess.web.dao;

import chess.board.piece.Piece;

import java.util.List;

public interface PieceDao {
    void updatePieceByPositionAndBoardId(final String type, final String team, final String position, final Long boardId);

    List<Piece> findAllByBoardId(final Long boardId);

    void save(final List<Piece> pieces, final Long boardId);

    void deleteByBoardId(Long boardId);
}
