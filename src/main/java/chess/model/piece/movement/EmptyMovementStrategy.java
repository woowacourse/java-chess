package chess.model.piece.movement;

import chess.model.position.Distance;

public final class EmptyMovementStrategy implements MovementStrategy {

    public static final EmptyMovementStrategy MOVEMENT = new EmptyMovementStrategy();

    private EmptyMovementStrategy() {
    }

    @Override
    public boolean movable(final Distance ignoredDistance, final AttackEvaluator ignoredAttackEvaluator) {
        return false;
    }
}
