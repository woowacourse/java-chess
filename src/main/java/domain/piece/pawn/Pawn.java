package domain.piece.pawn;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.move.Coordinate;
import domain.piece.move.Direction;
import domain.piece.move.Inclination;

public abstract class Pawn extends Piece {

    private static final double POINT = 1;

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(final Coordinate start, final Coordinate end) {
        Inclination inclination = Inclination.of(start.getInclination(end));

        return direction().canBeDirectionOf(inclination) &&
                isReachableDistance(start, end);
    }

    @Override
    public boolean isAttackable(final Coordinate start, final Coordinate end, final Piece target) {
        Inclination inclination = Inclination.of(start.getInclination(end));

        return attackDirection().canBeDirectionOf(inclination) &&
                isReachableDistance(start, end);
    }

    private boolean isReachableDistance(final Coordinate start, final Coordinate end) {
        if (start.getRow() == startRank()) {
            return start.hasDistanceLessThanTwo(end);
        }
        return start.hasDistanceLessThanOne(end);
    }

    public abstract Direction direction();
    public abstract Direction attackDirection();
    public abstract int startRank();

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
