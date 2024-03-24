package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;

public abstract class AbstractPawn extends UnslidingPiece {
    protected AbstractPawn(final Color color, Square square) {
        super(color, PieceType.PAWN, square);
    }
}
