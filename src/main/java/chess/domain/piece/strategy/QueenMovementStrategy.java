package chess.domain.piece.strategy;

import chess.domain.Direction;
import chess.domain.position.Position;

import java.util.List;

public class QueenMovementStrategy implements MovementStrategy {
    private static final List<Direction> QUEEN_DIRECTION = List.of(Direction.TOP, Direction.DOWN, Direction.RIGHT, Direction.LEFT,
            Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);

    private QueenMovementStrategy() {
    }

    public static final QueenMovementStrategy INSTANCE = new QueenMovementStrategy();

    public static QueenMovementStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isMovable(final Position source, final Position target) {
        Direction direction = Direction.of(source, target);
        return isMovableDirection(direction) && isMovableDistance(source, target);
    }

    private boolean isMovableDirection(final Direction direction) {
        return QUEEN_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(final Position source, final Position target) {
        int distance = source.calculateDistanceTo(target);
        return distance > 0;
    }
}
