package chess.domain.position.move;

import chess.domain.piece.Piece;

public final class PawnCatchMove implements PieceMove {

    @Override
    public boolean isMovable(final Piece piece, final boolean isLastPiece) {
        if (isLastPiece && piece.isEmpty()) {
            return false;
        }

        return !piece.isEmpty();
    }
}
