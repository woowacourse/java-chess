package domain.piece;

import domain.board.Color;
import domain.board.position.Vector;

public class King extends Piece {

    public King(final Color color) {
        super(color);
    }

    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        return vector.allAbsoluteValueSmallerOrEqualThan(1);
    }

}
