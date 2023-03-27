package chess.dao;

import java.util.List;

import chess.dao.entity.PieceEntity;

public interface PieceDao {

    Long findRecentPieceId();

    void savePiece(PieceEntity pieceEntity);

    void updatePiecePosition(PieceEntity pieceEntityToUpdate, PieceEntity pieceEntityToFind);

    void deletePieceByPosition(PieceEntity pieceEntityToDelete);

    List<PieceEntity> findAllPieceByGameId(Long gameId);
}
