package domain.piece;

import domain.board.Color;
import domain.board.position.Vector;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        return vector.isDiagonal();
    }
}
