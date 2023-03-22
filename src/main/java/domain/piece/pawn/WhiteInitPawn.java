package domain.piece.pawn;

import domain.piece.move.Coordinate;
import domain.piece.move.Direction;
import domain.piece.move.Inclination;
import domain.piece.Color;

import java.util.List;

public final class WhiteInitPawn extends Pawn {

    private static final Direction DIRECTION = new Direction(List.of(
            Inclination.POSITIVE_INFINITY
    ));

    private static final Direction DIRECTION_WHEN_ENEMY_EXIST = new Direction(List.of(
            Inclination.POSITIVE_INFINITY, Inclination.ONE, Inclination.MINUS_ONE
    ));

    public WhiteInitPawn(final Color color) {
        super(color);
    }

    @Override
    public boolean isMovableWhenMovingNotVariates(
            final Coordinate start,
            final Coordinate end
    ) {
        Inclination inclination = Inclination.of(start.getInclination(end));

        return DIRECTION.canBeDirectionOf(inclination) &&
                start.hasDistanceLessThanTwo(end);
    }

    @Override
    protected boolean isMovableWhenMovingVariates(
            final Coordinate start,
            final Coordinate end
    ) {
        Inclination inclination = Inclination.of(start.getInclination(end));

        return DIRECTION_WHEN_ENEMY_EXIST.canBeDirectionOf(inclination) &&
                start.hasDistanceLessThanOne(end);
    }
}
