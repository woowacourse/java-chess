package chess.domain.piece.strategy;

import chess.domain.Direction;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;

import java.util.List;

public class BlackPawnMovementStrategy implements MovementStrategy {
    private static final List<Direction> BLACK_DIRECTION = List.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);

    private BlackPawnMovementStrategy() {
    }

    public static final BlackPawnMovementStrategy INSTANCE = new BlackPawnMovementStrategy();

    public static BlackPawnMovementStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        Direction direction = Direction.of(source, target);
        return isMovableDirection(direction) && isMovableDistance(source, target, direction);
    }

    private boolean isMovableDirection(Direction direction) {
        return BLACK_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(Position source, Position target, Direction direction) {
        int distance = source.calculateDistanceTo(target);
        if (source.isRank(ChessRank.SEVEN) && direction == Direction.DOWN) {
            return (distance == 1 || distance == 2);
        }
        return distance == 1;
    }
}
