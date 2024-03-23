package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Type;
import domain.piece.info.Vector;

public class King extends Piece {

    public King(final Color color, final Type type) {
        super(color, type);
    }

    @Override
    protected boolean isReachable(final Vector vector) {
        return vector.isUnitVector();
    }

}
