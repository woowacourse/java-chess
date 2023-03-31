package chess.dao;

import java.util.List;

import chess.dao.entity.PieceEntity;
import chess.domain.piece.Piece;

public interface PieceDao {

    long findRecentPieceId();

    void savePiece(Piece piece, long gameId);

    void deletePieceByGameId(Piece piece, long gameId);

    List<PieceEntity> findAllPieceByGameId(long gameId);
}
