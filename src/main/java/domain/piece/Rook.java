package domain.piece;

import domain.position.Direction;
import domain.position.Position;

import java.util.List;
import java.util.Set;

public final class Rook extends Piece {

    public static final Set<Direction> ROOK_MOVABLE_DIRECTIONS = Set.of(Direction.CROSS);

    public Rook(Color color) {
        super(PieceName.ROOK, color);
    }

    @Override
    public boolean isMovablePath(Position start, List<Position> path) {
        return isMovableDirection(start, path.get(0));
    }

    @Override
    protected boolean isMovableDirection(Position start, Position nextPosition) {
        Direction nextDirection = Direction.of(start, nextPosition);
        return ROOK_MOVABLE_DIRECTIONS.contains(nextDirection);
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        throw new UnsupportedOperationException("[ERROR] Rook 객체에서는 지원하지 않는 기능입니다.");
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
