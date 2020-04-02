package chess.piece;

import chess.coordinate.Vector;

public class MovedPawn extends AbstractPawn {
    public MovedPawn(final Team team) {
        super(team);
    }

    @Override
    protected boolean canMoveTwoStep(final Vector vector, final Piece targetPiece) {
        return false;
    }
}
