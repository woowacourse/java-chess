package domain.piece;

import domain.position.Direction;
import domain.position.Path;
import domain.position.Position;

import java.util.Set;

public final class King extends Piece {

    public static final Set<Direction> KING_MOVABLE_DIRECTIONS = Set.of(Direction.CROSS, Direction.DIAGONAL);

    public King(Color color) {
        super(PieceName.KING, color);
    }

    @Override
    public boolean isMovablePath(Position start, Path path) {
        return isMovableDirection(start, path.getFirstPosition()) &&
                isMovableDistance(path.size());
    }

    @Override
    protected boolean isMovableDirection(Position start, Position nextPosition) {
        Direction nextDirection = Direction.of(start, nextPosition);
        return KING_MOVABLE_DIRECTIONS.contains(nextDirection);
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        return distance == 1;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
