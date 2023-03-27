package chess.dao;

import chess.domain.piece.Piece;

import java.util.Set;

public interface PieceDao {

    void addPiece(final Piece piece);

    void deletePiece(final Piece piece);

    Set<Piece> findAllPieceInGame();

    void deleteAllInGame();
}
