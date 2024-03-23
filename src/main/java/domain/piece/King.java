package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Type;
import domain.piece.info.Vector;

public class King extends Piece {

    public King(final Color color) {
        super(color, Type.KING);
    }

    @Override
    protected boolean isReachable(final Vector vector, final Piece targetPiece) {
        return vector.isUnitVector();
    }

}
