package chess.domain.piece.strategy;

import chess.domain.Direction;
import chess.domain.position.Position;

import java.util.List;

public class KnightMovementStrategy implements MovementStrategy {
    private static final List<Direction> KING_DIRECTION = List.of(Direction.TOP, Direction.DOWN, Direction.RIGHT, Direction.LEFT,
            Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);

    private KnightMovementStrategy() {
    }

    public static final KnightMovementStrategy INSTANCE = new KnightMovementStrategy();

    public static KnightMovementStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isMovable(final Position source, final Position target) {
        int fileDistance = source.calculateFileDistanceTo(target);
        int rankDistance = source.calculateRankDistanceTo(target);

        return (fileDistance == 1 && rankDistance == 2) || (fileDistance == 2 && rankDistance == 1);
    }
}
