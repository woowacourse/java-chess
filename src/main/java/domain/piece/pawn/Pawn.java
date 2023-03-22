package domain.piece.pawn;

import domain.piece.move.Situation;
import domain.piece.move.Coordinate;
import domain.piece.Piece;

public abstract class Pawn extends Piece {

    @Override
    public boolean isReachableByRule(
            final Coordinate start,
            final Coordinate end,
            final Situation situation
    ) {
        if (situation.meetNeutral()) {
            return isReachableByRuleWhenNoEnemy(start, end);
        }
        return isReachableByRuleWhenThereIsEnemy(start, end);
    }

    protected abstract boolean isReachableByRuleWhenNoEnemy(
            final Coordinate start,
            final Coordinate end
    );

    protected abstract boolean isReachableByRuleWhenThereIsEnemy(
            final Coordinate start,
            final  Coordinate end
    );

    @Override
    public boolean isPawn() {
        return true;
    }
}
