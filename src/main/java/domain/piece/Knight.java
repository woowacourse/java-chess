package domain.piece;

import domain.board.position.Vector;

public class Knight extends Piece {
    private static final double SCORE = 2.5;

    public Knight(final Color color) {
        super(color,SCORE);
    }

    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        return vector.hasAbsoluteValueOf(1) && vector.hasAbsoluteValueOf(2);
    }
}
