package chess.piece;

import chess.coordinate.Vector;

public class NotMovedPawn extends AbstractPawn {

    public static final int FIRST_MOVE_RANGE = 2;

    public NotMovedPawn(final Team team) {
        super(team);
    }

    @Override
    protected boolean canMoveTwoStep(final Vector vector, final Piece targetPiece) {
        return isSameDirection(vector, FIRST_MOVE_RANGE) && vector.isStraight();
    }

    @Override
    public Piece move() {
        return Pieces.findBy(MovedPawn.class, this.team);
    }
}
