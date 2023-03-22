package domain.piece.pawn;

import domain.piece.move.Situation;
import domain.piece.move.Coordinate;
import domain.piece.Color;
import domain.piece.Piece;

public abstract class Pawn extends Piece {

    private static final double POINT = 1;

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(
            final Coordinate start,
            final Coordinate end,
            final Situation situation
    ) {
        if (situation.meetNeutral()) {
            return isMovableWhenMovingNotVariates(start, end);
        }
        return isMovableWhenMovingVariates(start, end);
    }

    protected abstract boolean isMovableWhenMovingNotVariates(
            final Coordinate start,
            final Coordinate end
    );

    protected abstract boolean isMovableWhenMovingVariates(
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
