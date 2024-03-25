package domain.piece;

import domain.board.position.Vector;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        return vector.hasAbsoluteValueOf(1) && vector.hasAbsoluteValueOf(2);
    }
}
