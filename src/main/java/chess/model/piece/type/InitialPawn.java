package chess.model.piece.type;

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
import chess.model.piece.Piece;
import chess.model.position.Distance;
import java.util.List;
import java.util.Map;

public class InitialPawn extends Piece {

    private static final Map<Camp, Direction> moveDirections = Map.of(
            WHITE, NORTH,
            BLACK, SOUTH
    );
    private static final Map<Camp, List<Direction>> attackDirection = Map.of(
            WHITE, List.of(NORTH_EAST, NORTH_WEST),
            BLACK, List.of(SOUTH_EAST, SOUTH_WEST)
    );
    private static final int MINIMUM_DISTANCE = 1;
    private static final int MAXIMUM_DISTANCE = 2;

    private final Piece pawn;

    public InitialPawn(final Piece pawn) {
        super(pawn.camp());
        this.pawn = pawn;
    }

    @Override
    public boolean movable(final Distance distance, final Piece target) {
        if (isNotAvailableStraightMove(distance, target)) {
            return false;
        }
        if (isAttackAble(distance, target)) {
            return true;
        }
        return isAvailableDirection(distance);
    }

    private boolean isNotAvailableStraightMove(final Distance distance, final Piece target) {
        return target.isNotPassable() && distance.matchByDirection(straightDirectionByCamp());
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
        final int rank = Math.abs(distance.rank());

        return rank == MINIMUM_DISTANCE;
    }

    @Override
    protected boolean isAvailableDirection(final Distance distance) {
        if (isUnStraightDirection(distance)) {
            return false;
        }
        return isAvailableTravelDistance(distance);
    }

    private boolean isUnStraightDirection(final Distance distance) {
        return !distance.matchByDirection(straightDirectionByCamp());
    }

    private boolean isAvailableTravelDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());

        return rank >= MINIMUM_DISTANCE && rank <= MAXIMUM_DISTANCE;
    }

    private Direction straightDirectionByCamp() {
        return moveDirections.get(camp());
    }

    @Override
    public Piece pick() {
        return this.pawn;
    }
}
