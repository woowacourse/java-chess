package chess.model.piece.movement;

import static chess.model.piece.Direction.NORTH_EAST_EAST;
import static chess.model.piece.Direction.NORTH_NORTH_EAST;
import static chess.model.piece.Direction.NORTH_NORTH_WEST;
import static chess.model.piece.Direction.NORTH_WEST_WEST;
import static chess.model.piece.Direction.SOUTH_EAST_EAST;
import static chess.model.piece.Direction.SOUTH_SOUTH_EAST;
import static chess.model.piece.Direction.SOUTH_SOUTH_WEST;
import static chess.model.piece.Direction.SOUTH_WEST_WEST;

import chess.model.piece.Direction;
import chess.model.position.Distance;
import java.util.List;

public final class KnightMovementStrategy implements MovementStrategy {

    public static final KnightMovementStrategy MOVEMENT = new KnightMovementStrategy();
    private static final List<Direction> DIRECTIONS = List.of(
            NORTH_NORTH_EAST, NORTH_NORTH_WEST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST,
            NORTH_WEST_WEST, NORTH_EAST_EAST, SOUTH_WEST_WEST, SOUTH_EAST_EAST
    );
    private static final int MINIMUM_DISTANCE = 1;
    private static final int MAXIMUM_DISTANCE = 2;

    private KnightMovementStrategy() {
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

        return (rank != MINIMUM_DISTANCE || file != MAXIMUM_DISTANCE)
                && (rank != MAXIMUM_DISTANCE || file != MINIMUM_DISTANCE);
    }
}
