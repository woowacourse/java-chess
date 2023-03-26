package domain.piece.nonsliding;

import domain.piece.move.Coordinate;
import domain.piece.move.Direction;
import domain.piece.move.Inclination;
import domain.piece.Color;
import domain.piece.Piece;

import java.util.List;

public final class King extends Piece {

    private static final Direction DIRECTION = new Direction(List.of(
            Inclination.ONE, Inclination.MINUS_ONE, Inclination.NEGATIVE_INFINITY,
            Inclination.POSITIVE_INFINITY, Inclination.ZERO, Inclination.MINUS_ZERO
    ));
    private static final double POINT = 0;

    public King(final Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(
            final Coordinate start,
            final Coordinate end
    ) {
        Inclination inclination = Inclination.of(start.getInclination(end));

        return DIRECTION.canBeDirectionOf(inclination) &&
                start.hasDistanceLessThanOne(end);
    }

    @Override
    public boolean isAttackable(
            final Coordinate start,
            final Coordinate end,
            final Piece target
    ) {
        return isMovable(start, end);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
