package chess.piece;

import chess.coordinate.Vector;

public abstract class AbstractPawn extends Piece {

    private static final int PAWN_SCORE = 1;

    public AbstractPawn(final Team team) {
        super(team, PAWN_SCORE);
    }

    @Override
    public boolean canMove(final Vector vector, final Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }
        if (canMoveTwoStep(vector, targetPiece)) {
            return true;
        }
        if (!isSameDirection(vector, 1)) {
            return false;
        }
        if (targetPiece.isBlank()) {
            return vector.isStraight();
        }
        return vector.isDiagonal();
    }

    protected boolean isSameDirection(final Vector vector, int value) {
        return vector.isRangeUnderAbsolute(value) && team.isSameDirection(vector.getRankVariation());
    }

    protected abstract boolean canMoveTwoStep(final Vector vector, final Piece targetPiece);
}
