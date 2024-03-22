package chess.domain.piece.strategy;

import chess.domain.Direction;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;

import java.util.List;

public class WhitePawnMovementStrategy implements MovementStrategy {
    private static final List<Direction> WHITE_DIRECTION = List.of(Direction.TOP, Direction.TOP_LEFT, Direction.TOP_RIGHT);

    private WhitePawnMovementStrategy() {
    }

    public static final WhitePawnMovementStrategy INSTANCE = new WhitePawnMovementStrategy();

    public static WhitePawnMovementStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        Direction direction = Direction.of(source, target);
        return isMovableDirection(direction) && isMovableDistance(source, target, direction);
    }

    private boolean isMovableDirection(Direction direction) {
        return WHITE_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(Position source, Position target, Direction direction) {
        int distance = source.calculateDistanceTo(target);

        if (source.isRank(ChessRank.TWO) && direction == Direction.TOP) {
            return (distance == 1 || distance == 2);
        }
        return distance == 1;
    }
}
