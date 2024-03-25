package domain.piece;

import domain.board.position.Vector;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        return vector.isStraightOrDiagonal();
    }
}
