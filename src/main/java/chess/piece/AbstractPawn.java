package chess.piece;

import chess.coordinate.Vector;
import chess.piece.movestrategy.PawnMoveStrategyGroup;

public abstract class AbstractPawn extends Piece {

    private static final int PAWN_SCORE = 1;

    public AbstractPawn(final Team team) {
        super(team, PAWN_SCORE);
    }

    @Override
    protected boolean canReach(Vector vector, Piece targetPiece) {
        if (canMoveTwoStep(vector, targetPiece)) {
            return targetPiece.isBlank();
        }

        // 같은방향이면서 방향의 절대값이 1,1 혹은 0,1을 만족해야 한다.
        if (!(vector.isRangeUnderAbsolute(1) && team.isSameDirection(vector.getRankVariation()))) {
            return false;
        }

        return PawnMoveStrategyGroup.movable(vector, targetPiece);
    }

    protected abstract boolean canMoveTwoStep(final Vector vector, final Piece targetPiece);
}
