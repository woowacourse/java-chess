package chess.domain.position.move;

import chess.domain.piece.Piece;

public final class PassingMove implements PieceMove {

    @Override
    public boolean isMovable(final Piece piece, final boolean isLastPiece) {
        return true;
    }
}
