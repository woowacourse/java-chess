package chess.domain.piece;

import chess.domain.coordinate.Vector;

public abstract class AbstractPawn extends Piece {

    private static final int PAWN_SCORE = 1;
    private static final int PAWN_MOVE_RANGE = 1;

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
        if (!(vector.isRangeUnderAbsolute(PAWN_MOVE_RANGE) && team.isSameDirection(vector.getRankVariation()))) {
            return false;
        }
        if (targetPiece.isBlank()) {
            return vector.isStraight();
        }
        return vector.isDiagonal();
    }

    protected abstract boolean canMoveTwoStep(final Vector vector, final Piece targetPiece);
}
