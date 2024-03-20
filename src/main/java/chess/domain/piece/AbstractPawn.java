package chess.domain.piece;

import chess.domain.attribute.Color;

public abstract class AbstractPawn extends UnslidingPiece {
    protected AbstractPawn(final Color color) {
        super(color, PieceType.PAWN);
    }
}
