package domain.piece;

import domain.board.position.Vector;


public class Rook extends Piece {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        return vector.isStraight();
    }

}
