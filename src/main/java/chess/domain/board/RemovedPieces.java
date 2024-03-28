package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.ArrayList;
import java.util.List;

public class RemovedPieces {
    private final List<Piece> removedPiece;

    public RemovedPieces() {
        this.removedPiece = new ArrayList<>();
    }

    public void addPiece(final Piece piece) {
        removedPiece.add(piece);
    }

    public boolean isRecentlyRemovedPieceType(final PieceType pieceType) {
        if (!removedPiece.isEmpty()) {
            Piece recentlyRemovedPiece = removedPiece.get(removedPiece.size() - 1);
            return recentlyRemovedPiece.getType() == pieceType;
        }
        return false;
    }
}
