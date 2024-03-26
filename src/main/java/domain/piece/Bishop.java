package domain.piece;

import domain.board.position.Vector;

public class Bishop extends Piece {
    private static final double SCORE = 3;

    public Bishop(final Color color) {
        super(color, SCORE);
    }

    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        return vector.isDiagonal();
    }
}
