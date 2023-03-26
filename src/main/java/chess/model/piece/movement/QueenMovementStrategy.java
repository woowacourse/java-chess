package chess.model.piece.movement;

import chess.model.piece.Direction;
import chess.model.position.Distance;
import java.util.ArrayList;
import java.util.List;

public final class QueenMovementStrategy implements MovementStrategy {

    public static final QueenMovementStrategy MOVEMENT = new QueenMovementStrategy();
    private static final List<Direction> availableDirections = new ArrayList<>();

    static {
        availableDirections.addAll(Direction.DIAGONAL);
        availableDirections.addAll(Direction.ORTHOGONAL);
    }

    private QueenMovementStrategy() {
    }

    @Override
    public boolean movable(final Distance distance, final AttackEvaluator attackEvaluator) {
        return attackEvaluator.isAttackAble() && isAvailableDirection(distance);
    }

    private boolean isAvailableDirection(final Distance distance) {
        return availableDirections.stream()
                .anyMatch(distance::matchByDirection);
    }
}
