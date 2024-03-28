package domain.piece;

import domain.board.position.Vector;

public class King extends Piece {
    private static final double SCORE = 0;

    public King(final Color color) {
        super(color, SCORE);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        return vector.allAbsoluteValueSmallerOrEqualThan(1);
    }
}
