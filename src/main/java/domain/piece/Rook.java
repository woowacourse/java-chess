package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Type;
import domain.piece.info.Vector;


public class Rook extends Piece {

    public Rook(final Color color) {
        super(color, Type.ROOK);
    }

    @Override
    protected boolean isReachable(final Vector vector, final Piece targetPiece) {
        return vector.isStraight();
    }

}
