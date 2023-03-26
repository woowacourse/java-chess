package chess.model.piece.movement;

import static chess.model.piece.Camp.BLACK;
import static chess.model.piece.Camp.WHITE;
import static chess.model.piece.Direction.NORTH;
import static chess.model.piece.Direction.SOUTH;

import chess.model.piece.Camp;
import chess.model.piece.Direction;
import chess.model.position.Distance;
import java.util.Map;

public final class InitialPawnMovementStrategy implements MovementStrategy {

    public static final InitialPawnMovementStrategy MOVEMENT = new InitialPawnMovementStrategy();
    private static final Map<Camp, Direction> MOVE_DIRECTIONS = Map.of(
            WHITE, NORTH,
            BLACK, SOUTH
    );
    private static final int INITIAL_DISTANCE = 2;

    private InitialPawnMovementStrategy() {
    }

    @Override
    public boolean movable(final Distance distance, final AttackEvaluator attackEvaluator) {
        if (attackEvaluator.isEmpty() && isAvailableDirection(distance, attackEvaluator)) {
            return true;
        }
        return PawnMovementStrategy.MOVEMENT.movable(distance, attackEvaluator);
    }

    private boolean isAvailableDirection(final Distance distance, final AttackEvaluator attackEvaluator) {
        final Direction availableDirection = MOVE_DIRECTIONS.get(attackEvaluator.sourceCamp());

        return isAvailableDistance(distance) && distance.matchByDirection(availableDirection);
    }

    private boolean isAvailableDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());

        return rank == PawnMovementStrategy.MOVE_DISTANCE || rank == INITIAL_DISTANCE;
    }
}
