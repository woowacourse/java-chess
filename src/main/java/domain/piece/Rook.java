package domain.piece;

import domain.position.Direction;
import domain.position.Position;

import java.util.List;

public final class Rook extends Piece {

    private static final List<Direction> ROOK_MOVABLE_DIRECTIONS = List.of(Direction.CROSS);

    public Rook(Color color) {
        super(PieceName.ROOK, color);
    }

    @Override
    public boolean isMovablePath(Position start, List<Position> path) {
        return isMovableDirection(start, path.get(0)) &&
                isMovableDistance(path.size());
    }

    @Override
    protected boolean isMovableDirection(Position start, Position nextPosition) {
        Direction nextDirection = Direction.of(start, nextPosition);
        return ROOK_MOVABLE_DIRECTIONS.contains(nextDirection);
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        return true;
    }
}
