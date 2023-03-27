package domain.piece;

import domain.position.Direction;
import domain.position.Position;

import java.util.Set;

public final class Bishop extends SlidingPiece {

    public static final Set<Direction> BISHOP_MOVABLE_DIRECTIONS = Set.of(Direction.DIAGONAL);

    public Bishop(Color color) {
        super(PieceName.BISHOP, color);
    }

    @Override
    protected boolean isMovableDirection(Position start, Position nextPosition) {
        Direction nextDirection = Direction.of(start, nextPosition);
        return BISHOP_MOVABLE_DIRECTIONS.contains(nextDirection);
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        throw new UnsupportedOperationException("[ERROR] Bishop 객체에서는 지원하지 않는 기능입니다.");
    }
}
