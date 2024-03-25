package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.ArrayList;
import java.util.List;

public class RemovedPiece {
    private final List<Piece> removedPiece;

    public RemovedPiece() {
        this.removedPiece = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        removedPiece.add(piece);
    }

    public boolean isRecentlyRemovedPieceType(PieceType pieceType) {
        if (!removedPiece.isEmpty()) {
            Piece recentlyRemovedPiece = removedPiece.get(removedPiece.size() - 1);
            return recentlyRemovedPiece.getType() == pieceType;
        }
        return false;
    }
}
