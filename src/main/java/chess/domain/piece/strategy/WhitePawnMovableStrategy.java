package chess.domain.piece.strategy;

import static chess.domain.direction.Direction.UP;
import static chess.domain.direction.Direction.UP_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT;

import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public final class WhitePawnMovableStrategy extends PawnMovableStrategy {

    private static final int MOVABLE_COUNT = 1;
    private static final Direction MOVE_DIRECTION = UP;
    private static final List<Direction> MOVE_DIRECTION_TO_ENEMY = Arrays.asList(UP_RIGHT, UP_LEFT);

    private WhitePawnMovableStrategy(int movableCount, Direction moveDirection,
                                     List<Direction> moveDirectionToEnemy) {
        super(movableCount, moveDirection, moveDirectionToEnemy);
    }

    public WhitePawnMovableStrategy() {
        this(MOVABLE_COUNT, MOVE_DIRECTION, MOVE_DIRECTION_TO_ENEMY);
    }
}
