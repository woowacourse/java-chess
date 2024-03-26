package domain.piece;

import domain.board.position.Vector;

public class Queen extends Piece {

    private static final double SCORE = 9;

    public Queen(final Color color) {
        super(color,SCORE);
    }

    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        return vector.isStraightOrDiagonal();
    }
}
