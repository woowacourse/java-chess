package chess.dao;

import chess.domain.piece.Piece;

import java.util.HashSet;
import java.util.Set;

public class InMemoryPieceDao implements PieceDao{

    private final Set<Piece> pieces = new HashSet<>();

    @Override
    public Set<Piece> findAllPieceInGame(final int gameRoomId) {
        return Set.copyOf(pieces);
    }

    @Override
    public void deleteAllInGame(final int gameRoomId) {
        pieces.removeAll(pieces);
    }

    @Override
    public void updatePieces(final int gameRoomId, final Set<Piece> pieces) {
        deleteAllInGame(gameRoomId);
        this.pieces.addAll(pieces);
    }

    @Override
    public void addPiece(final int gameRoomId, final Piece piece) {
        pieces.add(piece);
    }
}
