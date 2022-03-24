package chess.domain.piece.strategy;

import static chess.domain.direction.Direction.DOWN;
import static chess.domain.direction.Direction.DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT;

import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public final class BlackPawnMovableStrategy extends PawnMovableStrategy {

    private static final int MOVABLE_COUNT = 1;
    private static final Direction MOVE_DIRECTION = DOWN;
    private static final List<Direction> MOVE_DIRECTION_TO_ENEMY = Arrays.asList(DOWN_RIGHT, DOWN_LEFT);

    private BlackPawnMovableStrategy(int movableCount, Direction moveDirection,
                                     List<Direction> moveDirectionToEnemy) {
        super(movableCount, moveDirection, moveDirectionToEnemy);
    }

    public BlackPawnMovableStrategy() {
        this(MOVABLE_COUNT, MOVE_DIRECTION, MOVE_DIRECTION_TO_ENEMY);
    }
}
