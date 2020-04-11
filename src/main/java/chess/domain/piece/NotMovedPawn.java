package chess.domain.piece;

import chess.domain.coordinate.Vector;

public class NotMovedPawn extends AbstractPawn {

    public static final int FIRST_MOVE_RANGE = 2;

    public NotMovedPawn(final Team team) {
        super(team);
    }

    @Override
    protected boolean canMoveTwoStep(final Vector vector, final Piece targetPiece) {
        return vector.isRangeUnderAbsolute(FIRST_MOVE_RANGE) && team.isSameDirection(vector.getRankVariation()) && vector.isStraight() && targetPiece.isBlank();
    }

    @Override
    public Piece move() {
        return Pieces.findBy(MovedPawn.class, this.team);
    }
}
