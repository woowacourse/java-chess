package chess.domain.piece.strategy;

import chess.domain.Direction;
import chess.domain.position.Position;

import java.util.List;

public class BishopMovementStrategy implements MovementStrategy {
    private static final List<Direction> BISHOP_DIRECTION = List.of(Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);

    private BishopMovementStrategy() {
    }

    public static final BishopMovementStrategy INSTANCE = new BishopMovementStrategy();

    public static BishopMovementStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isMovable(final Position source, final Position target) {
        Direction direction = Direction.of(source, target);
        return isMovableDirection(direction) && isMovableDistance(source, target);
    }

    private boolean isMovableDirection(Direction direction) {
        return BISHOP_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(Position source, Position target) {
        int distance = source.calculateDistanceTo(target);
        return distance > 0;
    }
}
