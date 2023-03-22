package domain.piece.pawn;

import domain.piece.move.Situation;
import domain.piece.move.Coordinate;
import domain.piece.Piece;

public abstract class Pawn extends Piece {

    private static final double POINT = 1;

    @Override
    public boolean isReachableByRule(
            final Coordinate start,
            final Coordinate end,
            final Situation situation
    ) {
        if (situation.meetNeutral()) {
            return isReachableByRuleWhenMovingNotVariates(start, end);
        }
        return isReachableByRuleWhenMovingVariates(start, end);
    }

    protected abstract boolean isReachableByRuleWhenMovingNotVariates(
            final Coordinate start,
            final Coordinate end
    );

    protected abstract boolean isReachableByRuleWhenMovingVariates(
            final Coordinate start,
            final  Coordinate end
    );

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
