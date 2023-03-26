package chess.dao.chess;

import chess.entity.PieceEntity;

import java.util.List;

public interface PieceDao {
    List<PieceEntity> findByChessGameId(final Long chessGameId);

    Long save(final PieceEntity pieceEntity);

    void deleteByPositions(final Long chessGameId, final PieceEntity... pieceEntity);
}
