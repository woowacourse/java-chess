package chess.model.piece.type;

import chess.model.Color;
import chess.model.piece.Direction;
import chess.model.piece.PieceType;
import chess.model.position.Distance;
import java.util.List;

public class Pawn extends Piece {

    private static final List<Direction> white = Direction.whitePawn();
    private static final List<Direction> black = Direction.blackPawn();
    private static final int MINIMUM_DISTANCE = 1;

    public Pawn(final Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    boolean isRightDirection(final Direction direction) {
        if (color.isWhite()) {
            return white.contains(direction);
        }

        return black.contains(direction);
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
