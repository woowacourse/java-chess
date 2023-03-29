package domain.piece;

import domain.position.Direction;
import domain.position.Position;

import java.util.Set;

public final class Queen extends SlidingPiece {

    public Queen(Color color) {
        super(PieceName.QUEEN, color);
    }

//    @Override
//    protected boolean isMovableDirection(Position start, Position nextPosition) {
//        Direction nextDirection = Direction.of(start, nextPosition);
//        return QUEEN_MOVABLE_DIRECTIONS.contains(nextDirection);
//    }

    @Override
    protected boolean isMovableDistance(int distance) {
        throw new UnsupportedOperationException("[ERROR] Queen 객체에서는 지원하지 않는 기능입니다.");
    }

    @Override
    protected Set<Direction> getMovableDirections() {
        return Set.of(Direction.CROSS, Direction.DIAGONAL);
    }
}
