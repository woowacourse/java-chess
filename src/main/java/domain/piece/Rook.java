package domain.piece;

import domain.board.position.Vector;


public class Rook extends Piece {
    private static final double SCORE = 5;

    public Rook(final Color color) {
        super(color,SCORE);
    }

    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        return vector.isStraight();
    }

}
