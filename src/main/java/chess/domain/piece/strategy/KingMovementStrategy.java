package chess.domain.piece.strategy;

import chess.domain.Direction;
import chess.domain.position.Position;

import java.util.List;

public class KingMovementStrategy implements MovementStrategy{
    private static final List<Direction> KING_DIRECTION = List.of(Direction.TOP, Direction.DOWN, Direction.RIGHT, Direction.LEFT,
            Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);

    private KingMovementStrategy(){}

    public static final KingMovementStrategy INSTANCE = new KingMovementStrategy();

    public static KingMovementStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isMovable(final Position source, final Position target) {
        Direction direction = Direction.of(source, target);
        return isMovableDirection(direction) && isMovableDistance(source, target);
    }

    private boolean isMovableDirection(Direction direction) {
        return KING_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(Position source, Position target) {
        int distance = source.calculateDistanceTo(target);
        return distance == 1;
    }
}
