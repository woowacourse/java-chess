package chess.model.piece.type;

import static chess.model.piece.Camp.*;
import static chess.model.piece.Direction.NORTH;
import static chess.model.piece.Direction.NORTH_EAST;
import static chess.model.piece.Direction.NORTH_WEST;
import static chess.model.piece.Direction.SOUTH;
import static chess.model.piece.Direction.SOUTH_EAST;
import static chess.model.piece.Direction.SOUTH_WEST;

import chess.model.piece.Camp;
import chess.model.piece.Direction;
import chess.model.piece.Piece;
import chess.model.position.Distance;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    private static final Map<Camp, Direction> moveDirections = Map.of(
            WHITE, NORTH,
            BLACK, SOUTH
    );
    private static final Map<Camp, List<Direction>> attackDirection = Map.of(
            WHITE, List.of(NORTH_EAST, NORTH_WEST),
            BLACK, List.of(SOUTH_EAST, SOUTH_WEST)
    );
    static final int MOVE_DISTANCE = 1;

    public Pawn(final Camp camp) {
        super(camp);
    }

    @Override
    public boolean movable(final Distance distance, final Piece target) {
        if (isUnMovable(distance, target)) {
            return false;
        }
        if (isAttackAble(distance, target)) {
            return true;
        }
        return movable(distance);
    }

    private boolean isUnMovable(final Distance distance, final Piece target) {
        return target.isNotPassable() && isMovableDirection(distance);
    }

    private boolean isAttackAble(final Distance distance, final Piece target) {
        return target.isNotPassable() && isEnemy(target)
                && isAttackDirection(distance) && isAttackAbleDistance(distance);
    }

    private boolean isEnemy(final Piece target) {
        return !target.isSameTeam(camp());
    }

    private boolean isAttackDirection(final Distance distance) {
        final List<Direction> availableDirections = attackDirection.get(camp());

        return availableDirections.stream()
                .anyMatch(distance::matchByDirection);
    }

    private boolean isAttackAbleDistance(final Distance distance) {
        return !isUnAvailableDistance(distance);
    }

    @Override
    public boolean movable(final Distance distance) {
        if (isUnAvailableDistance(distance)) {
            return false;
        }
        return isMovableDirection(distance);
    }

    private boolean isUnAvailableDistance(final Distance  distance) {
        final int rank = Math.abs(distance.rank());

        return rank != MOVE_DISTANCE;
    }

    private boolean isMovableDirection(final Distance distance) {
        final Direction availableDirection = moveDirections.get(camp());

        return distance.matchByDirection(availableDirection);
    }
}
