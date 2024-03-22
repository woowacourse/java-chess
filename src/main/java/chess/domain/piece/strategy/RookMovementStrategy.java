package chess.domain.piece.strategy;

import chess.domain.Direction;
import chess.domain.position.Position;

import java.util.List;

public class RookMovementStrategy implements MovementStrategy {
    private static final List<Direction> ROOK_DIRECTION = List.of(Direction.TOP, Direction.DOWN, Direction.RIGHT, Direction.LEFT);

    private RookMovementStrategy() {
    }

    public static final RookMovementStrategy INSTANCE = new RookMovementStrategy();

    public static RookMovementStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isMovable(final Position source, final Position target) {
        Direction direction = Direction.of(source, target);
        return isMovableDirection(direction) && isMovableDistance(source, target);
    }

    private boolean isMovableDirection(Direction direction) {
        return ROOK_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(Position source, Position target) {
        int distance = source.calculateDistanceTo(target);
        return distance > 0;
    }
}
