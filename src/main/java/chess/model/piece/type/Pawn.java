package chess.model.piece.type;

import static chess.model.position.Direction.NORTH;
import static chess.model.position.Direction.NORTH_EAST;
import static chess.model.position.Direction.NORTH_WEST;
import static chess.model.position.Direction.SOUTH;
import static chess.model.position.Direction.SOUTH_EAST;
import static chess.model.position.Direction.SOUTH_WEST;

import chess.model.Color;
import chess.model.piece.PieceType;
import chess.model.position.Direction;
import chess.model.position.Distance;
import java.util.Set;

public class Pawn extends Piece {

    private static final Set<Direction> WHITE = Set.of(NORTH, NORTH_EAST, NORTH_WEST);
    private static final Set<Direction> BLACK = Set.of(SOUTH, SOUTH_EAST, SOUTH_WEST);
    private static final int MINIMUM_DISTANCE = 1;

    public Pawn(final Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    boolean isRightDirection(final Direction direction) {
        if (color.isWhite()) {
            return WHITE.contains(direction);
        }

        return BLACK.contains(direction);
    }

    @Override
    protected boolean isRightDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());

        return rank == MINIMUM_DISTANCE;
    }

    @Override
    protected boolean isSatisfySpecialCondition(final Distance distance, final Color targetColor) {
        final int rank = Math.abs(distance.rank());

        final Direction direction = distance.findDirection();

        return isDiagonalMoveCondition(targetColor, rank, direction) || isStraightMoveCondition(targetColor, direction);
    }

    private static boolean isDiagonalMoveCondition(final Color targetColor, final int rank, final Direction direction) {
        return Direction.isDiagonal(direction) && rank == MINIMUM_DISTANCE && targetColor.isNotEmpty();
    }

    private static boolean isStraightMoveCondition(final Color targetColor, final Direction direction) {
        return Direction.isUpOrDown(direction) && targetColor.isEmpty();
    }

    @Override
    protected boolean isRightAttack(final Distance distance, final Color targetColor) {
        return hasEnemy(targetColor) && isDiagonalAttack(distance);
    }

    private boolean hasEnemy(final Color color) {
        return color.isNotEmpty() && color.isDifferent(this.color);
    }

    private boolean isDiagonalAttack(final Distance distance) {
        return Direction.isDiagonal(distance.findDirection())
                && Math.abs(distance.rank()) == MINIMUM_DISTANCE;
    }
}
