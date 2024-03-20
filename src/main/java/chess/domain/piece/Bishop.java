package chess.domain.piece;

import chess.domain.attribute.Color;

public class Bishop extends SlidingPiece {
    public Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }
}
