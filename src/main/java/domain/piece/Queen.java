package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Type;
import domain.piece.info.Vector;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        return vector.isStraightOrDiagonal();
    }
}
