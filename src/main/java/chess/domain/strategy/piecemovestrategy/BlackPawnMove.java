package chess.domain.strategy.piecemovestrategy;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public final class BlackPawnMove extends PawnMoveStrategy {

    private static final Rank INITIAL_POSITION = Rank.SEVEN;
    private static final Color COLOR = Color.BLACK;

    public BlackPawnMove() {
        super(COLOR);
    }

    @Override
    boolean isMovableToEmpty(final Position from, final Position to) {
        if (from.isAtRank(INITIAL_POSITION)) {
            return isBonusMoveOfInitialPosition(from, to);
        }
        return isGoalPositionInFace(from, to);
    }

    private boolean isBonusMoveOfInitialPosition(final Position from, final Position to) {
        return isTargetInFront(from, to)
                && from.calculateVerticalDistance(to) == 2
                && from.calculateHorizontalDistance(to) == 0;
    }

    private boolean isGoalPositionInFace(final Position from, final Position to) {
        return isTargetInFront(from, to)
                && from.calculateVerticalDistance(to) == 1
                && from.calculateHorizontalDistance(to) == 0;
    }

    @Override
    boolean isMovableToEnemy(final Position from, final Position to) {
        return isTargetInFront(from, to)
                && from.calculateVerticalDistance(to) == 1
                && from.calculateHorizontalDistance(to) == 1;
    }

    private boolean isTargetInFront(final Position from, final Position to) {
        return to.isAtLowRank(from);
    }
}
