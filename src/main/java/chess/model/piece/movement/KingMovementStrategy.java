package chess.model.piece.movement;

import chess.model.piece.Direction;
import chess.model.position.Distance;
import java.util.ArrayList;
import java.util.List;

public final class KingMovementStrategy implements MovementStrategy {

    public static final KingMovementStrategy MOVEMENT = new KingMovementStrategy();
    private static final List<Direction> DIRECTIONS = new ArrayList<>();
    private static final int MINIMUM_DISTANCE = 0;
    private static final int MAXIMUM_DISTANCE = 1;

    static {
        DIRECTIONS.addAll(Direction.DIAGONAL);
        DIRECTIONS.addAll(Direction.ORTHOGONAL);
    }

    private KingMovementStrategy() {
    }

    @Override
    public boolean movable(final Distance distance, final AttackEvaluator attackEvaluator) {
        return attackEvaluator.isAttackAble() && isAvailableDirection(distance);
    }

    private boolean isAvailableDirection(final Distance distance) {
        if (isUnAvailableDistance(distance)) {
            return false;
        }
        return isMovableDirection(distance);
    }

    private boolean isMovableDirection(final Distance distance) {
        return DIRECTIONS.stream()
                .anyMatch(distance::matchByDirection);
    }

    private boolean isUnAvailableDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());
        final int file = Math.abs(distance.file());

        return (rank < MINIMUM_DISTANCE || rank > MAXIMUM_DISTANCE) ||
                (file < MINIMUM_DISTANCE || file > MAXIMUM_DISTANCE);
    }
}
