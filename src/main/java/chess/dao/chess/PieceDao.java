package chess.dao.chess;

import chess.entity.PieceEntity;

import java.util.List;

public interface PieceDao {
    List<PieceEntity> findByChessGameId(final long chessGameId);

    Long insert(final PieceEntity pieceEntity);

    void deleteByPositions(final long chessGameId, final PieceEntity... pieceEntity);

    void deleteByChessGameId(long chessGameId);
}
