package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Type;
import domain.piece.info.Vector;

public class Knight extends Piece {

    public Knight(final Color color, final Type type) {
        super(color, type);
    }

    @Override
    protected boolean isReachable(final Vector vector) {
        return vector.hasAbsoluteValueOf(1) && vector.hasAbsoluteValueOf(2);
    }

}
