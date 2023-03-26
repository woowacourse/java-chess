package chess.model.piece.movement;

import chess.model.piece.Direction;
import chess.model.position.Distance;
import java.util.List;

public final class RookMovementStrategy implements MovementStrategy {

    public static final RookMovementStrategy MOVEMENT = new RookMovementStrategy();
    private static final List<Direction> availableDirections = Direction.ORTHOGONAL;

    private RookMovementStrategy() {
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
