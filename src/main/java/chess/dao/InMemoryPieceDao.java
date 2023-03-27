package chess.dao;

import chess.domain.piece.Piece;

import java.util.HashSet;
import java.util.Set;

public class InMemoryPieceDao implements PieceDao{

    private final Set<Piece> pieces = new HashSet<>();

    @Override
    public void addPiece(final Piece piece) {
        pieces.add(piece);
    }

    @Override
    public void deletePiece(final Piece piece) {
        pieces.remove(piece);
    }

    @Override
    public Set<Piece> findAllPieceInGame() {
        return Set.copyOf(pieces);
    }

    @Override
    public void deleteAllInGame() {
        pieces.removeAll(pieces);
    }
}
