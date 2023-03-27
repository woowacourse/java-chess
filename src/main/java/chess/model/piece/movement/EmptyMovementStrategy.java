package chess.model.piece.movement;

import chess.model.position.Distance;

public final class EmptyMovementStrategy implements MovementStrategy {

    private static class MovementHolder {
        private static final MovementStrategy MOVEMENT = new EmptyMovementStrategy();
    }

    private EmptyMovementStrategy() {
    }

    public static MovementStrategy getInstance() {
        return MovementHolder.MOVEMENT;
    }

    @Override
    public boolean movable(final Distance ignoredDistance, final AttackEvaluator ignoredAttackEvaluator) {
        return false;
    }
}
