package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Type;
import domain.piece.info.Vector;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        return vector.hasAbsoluteValueOf(1) && vector.hasAbsoluteValueOf(2);
    }

}
