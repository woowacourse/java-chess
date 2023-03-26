package chess.model.piece.movement;

import static chess.model.piece.Camp.BLACK;
import static chess.model.piece.Camp.WHITE;
import static chess.model.piece.Direction.NORTH;
import static chess.model.piece.Direction.NORTH_EAST;
import static chess.model.piece.Direction.NORTH_WEST;
import static chess.model.piece.Direction.SOUTH;
import static chess.model.piece.Direction.SOUTH_EAST;
import static chess.model.piece.Direction.SOUTH_WEST;

import chess.model.piece.Camp;
import chess.model.piece.Direction;
import chess.model.position.Distance;
import java.util.List;
import java.util.Map;

public final class PawnMovementStrategy implements MovementStrategy {

    public static final PawnMovementStrategy MOVEMENT = new PawnMovementStrategy();
    private static final Map<Camp, Direction> MOVE_DIRECTIONS = Map.of(
            WHITE, NORTH,
            BLACK, SOUTH
    );
    private static final Map<Camp, List<Direction>> ATTACK_DIRECTION = Map.of(
            WHITE, List.of(NORTH_EAST, NORTH_WEST),
            BLACK, List.of(SOUTH_EAST, SOUTH_WEST)
    );
    static final int MOVE_DISTANCE = 1;

    private PawnMovementStrategy() {
    }

    @Override
    public boolean movable(final Distance distance, final AttackEvaluator attackEvaluator) {
        if (isNotAvailableStraightMove(distance, attackEvaluator)) {
            return false;
        }
        if (isAttackAble(distance, attackEvaluator)) {
            return true;
        }
        return isAvailableDirection(distance, attackEvaluator);
    }

    private boolean isNotAvailableStraightMove(final Distance distance, final AttackEvaluator attackEvaluator) {
        return !attackEvaluator.isEmpty() && isMovableDirection(distance, attackEvaluator);
    }

    private boolean isAttackAble(final Distance distance, final AttackEvaluator attackEvaluator) {
        return attackEvaluator.isEnemy()
                && isAttackAbleDirection(distance, attackEvaluator) && isAttackAbleDistance(distance);
    }

    private boolean isAttackAbleDirection(final Distance distance, final AttackEvaluator attackEvaluator) {
        final List<Direction> availableDirections = ATTACK_DIRECTION.get(attackEvaluator.sourceCamp());

        return availableDirections.stream()
                .anyMatch(distance::matchByDirection);
    }

    private boolean isAttackAbleDistance(final Distance distance) {
        return !isUnAvailableDistance(distance);
    }

    private boolean isAvailableDirection(final Distance distance, final AttackEvaluator attackEvaluator) {
        if (isUnAvailableDistance(distance)) {
            return false;
        }
        return isMovableDirection(distance, attackEvaluator);
    }

    private boolean isUnAvailableDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());

        return rank != MOVE_DISTANCE;
    }

    private boolean isMovableDirection(final Distance distance, final AttackEvaluator attackEvaluator) {
        final Direction availableDirection = MOVE_DIRECTIONS.get(attackEvaluator.sourceCamp());

        return distance.matchByDirection(availableDirection);
    }
}
