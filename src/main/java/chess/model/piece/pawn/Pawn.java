package chess.model.piece.pawn;

import chess.model.Color;
import chess.model.piece.Piece;
import chess.model.piece.PieceType;
import chess.model.position.Direction;
import chess.model.position.Distance;
import java.util.Set;

public abstract class Pawn extends Piece {

    private static final int MINIMUM_DISTANCE = 1;
    public static final Set<Direction> DIRECTIONS = Direction.diagonal();

    public Pawn(final Color color) {
        super(color, PieceType.PAWN);
    }

    public final boolean isMovable(final Distance distance, final Color targetColor) {
        return isRightMove(distance, targetColor) || isRightAttack(distance, targetColor);
    }

    private boolean isRightMove(final Distance distance, final Color targetColor) {
        return isRightDirection(distance.findDirection())
                && isRightDistance(distance)
                && isSatisfySpecialCondition(distance, targetColor);
    }

    protected abstract boolean isRightDirection(final Direction direction);

    protected abstract boolean isRightDistance(final Distance distance);

    public abstract Piece update();

    @Override
    public final boolean isPawn() {
        return true;
    }

    @Override
    public final boolean isEmpty() {
        return false;
    }

    @Override
    public final boolean isKing() {
        return false;
    }

    protected final boolean isSatisfySpecialCondition(final Distance distance, final Color targetColor) {
        final int rank = Math.abs(distance.rank());

        final Direction direction = distance.findDirection();

        return isDiagonalMoveCondition(targetColor, rank, direction) || isStraightMoveCondition(targetColor, direction);

    }

    private boolean isDiagonalMoveCondition(final Color targetColor, final int rank, final Direction direction) {
        return DIRECTIONS.contains(direction) && rank == MINIMUM_DISTANCE && targetColor.isNotEmpty();
    }

    private boolean isStraightMoveCondition(final Color targetColor, final Direction direction) {
        return Direction.isUpOrDown(direction) && targetColor.isEmpty();
    }

    protected final boolean isRightAttack(final Distance distance, final Color targetColor) {
        return hasEnemy(targetColor) && isDiagonalAttack(distance);
    }

    private boolean hasEnemy(final Color targetColor) {
        return targetColor.isNotEmpty() && targetColor.isDifferent(getColor());
    }

    private boolean isDiagonalAttack(final Distance distance) {
        return Direction.isDiagonal(distance.findDirection())
                && Math.abs(distance.rank()) == MINIMUM_DISTANCE;
    }
}
