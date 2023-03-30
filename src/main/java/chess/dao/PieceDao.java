package chess.dao;

import chess.domain.piece.Piece;

import java.util.Set;

public interface PieceDao {

    void addPiece(final int gameRoomId, final Piece piece);

    void deletePiece(final int gameRoomId, final Piece piece);

    Set<Piece> findAllPieceInGame(final int gameRoomId);

    void deleteAllInGame(final int gameRoomId);
}
