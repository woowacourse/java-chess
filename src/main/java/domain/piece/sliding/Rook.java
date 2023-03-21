package domain.piece.sliding;

import domain.piece.Piece;
import domain.piece.move.Coordinate;
import domain.piece.move.Direction;
import domain.piece.move.Inclination;

import java.util.List;

public final class Rook extends Piece {

    private static final Direction DIRECTION = new Direction(List.of(
            Inclination.NEGATIVE_INFINITY, Inclination.POSITIVE_INFINITY,
            Inclination.ZERO, Inclination.MINUS_ZERO
    ));

    @Override
    public boolean isReachableByRule(final Coordinate start, final Coordinate end) {
        Inclination inclination = Inclination.of(start.getInclination(end));

        return DIRECTION.canBeDirectionOf(inclination);
    }
}
