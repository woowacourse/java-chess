package chess.dao;

import chess.domain.piece.Piece;

import java.util.Set;

public interface PieceDao {

    Set<Piece> findAllPieceInGame(final int gameRoomId);

    void deleteAllInGame(final int gameRoomId);

    void updatePieces(final int gameRoomId, final Set<Piece> pieces);

    void addPiece(final int gameRoomId, final Piece piece);
}
