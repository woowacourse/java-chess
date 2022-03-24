package chess.domain.piece.strategy;

import static chess.domain.direction.Direction.*;

import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public final class BlackPawnFirstMovableStrategy extends PawnMovableStrategy {

    private static final int MOVABLE_COUNT = 2;
    private static final Direction MOVE_DIRECTION = DOWN;
    private static final List<Direction> MOVE_DIRECTION_TO_ENEMY = Arrays.asList(DOWN_RIGHT, DOWN_LEFT);

    private BlackPawnFirstMovableStrategy(int movableCount, Direction moveDirection,
                                          List<Direction> moveDirectionToEnemy) {
        super(movableCount, moveDirection, moveDirectionToEnemy);
    }

    public BlackPawnFirstMovableStrategy() {
        this(MOVABLE_COUNT, MOVE_DIRECTION, MOVE_DIRECTION_TO_ENEMY);
    }
}
