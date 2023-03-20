package domain.piece;

import domain.position.Direction;
import domain.position.Position;

import java.util.List;

public final class Queen extends Piece {

    private static final List<Direction> QUEEN_MOVABLE_DIRECTIONS = List.of(Direction.CROSS, Direction.DIAGONAL);

    public Queen(Color color) {
        super(PieceName.QUEEN, color);
    }

    @Override
    public boolean isMovablePath(Position start, List<Position> path) {
        return isMovableDirection(start, path.get(0)) &&
                isMovableDistance(path.size());
    }

    @Override
    protected boolean isMovableDirection(Position start, Position nextPosition) {
        Direction nextDirection = Direction.of(start, nextPosition);
        return QUEEN_MOVABLE_DIRECTIONS.contains(nextDirection);
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        return true;
    }
}
