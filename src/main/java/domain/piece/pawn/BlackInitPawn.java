package domain.piece.pawn;

import domain.piece.move.Coordinate;
import domain.piece.move.Direction;
import domain.piece.move.Inclination;

import java.util.List;

public final class BlackInitPawn extends Pawn {

    private static final Direction DIRECTION = new Direction(List.of(
            Inclination.NEGATIVE_INFINITY
    ));

    @Override
    public boolean isReachableByRule(final Coordinate start, final Coordinate end) {
        Inclination inclination = Inclination.of(start.getInclination(end));

        return DIRECTION.canBeDirectionOf(inclination) &&
                start.hasDistanceLessThanTwo(end);
    }
}
