package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class PawnPiece extends Piece {

    private static final String NAME = "P";
    private static final int LIMIT_DISTANCE = 2;

    public PawnPiece(final Color color) {
        super(color, NAME);
    }

    @Override
    public boolean isRightMovement(final Position from, final Position to, final boolean isEmptyTarget) {
        final int columnDistance = to.calculateColumnDistance(from);
        final int rankDistance = to.calculateRankDistance(from);

        final Direction direction = Direction.of(columnDistance, rankDistance);

        return isInitialForwardMove(direction, isEmptyTarget, from, rankDistance) ||
                isForwardMove(direction, isEmptyTarget, rankDistance) ||
                isDiagonalMove(direction, isEmptyTarget, rankDistance, columnDistance);
    }

    private boolean isInitialForwardMove(final Direction direction, final boolean isEmptyTarget,
                                         final Position from,
                                         final int rankDistance) {
        return isEmptyTarget && ((super.isSameColor(Color.BLACK) && from.isSameRank(Rank.SEVEN) &&
                direction == Direction.SOUTH && Math.abs(rankDistance) <= LIMIT_DISTANCE) ||
                (super.isSameColor(Color.WHITE) && from.isSameRank(Rank.TWO) &&
                        direction == Direction.NORTH && Math.abs(rankDistance) <= LIMIT_DISTANCE));
    }

    private boolean isForwardMove(final Direction direction, final Boolean isEmptyTarget,
                                  final int rankDistance) {
        return isForward(direction) && isEmptyTarget && Math.abs(rankDistance) < LIMIT_DISTANCE;
    }

    private boolean isForward(final Direction direction) {
        return (super.isSameColor(Color.WHITE) && direction == Direction.NORTH) ||
                (super.isSameColor(Color.BLACK) && direction == Direction.SOUTH);
    }

    private boolean isDiagonalMove(final Direction direction, final boolean isEmptyTarget,
                                   final int rankDistance, final int fileDistance) {
        return isDiagonal(direction) && !isEmptyTarget && Math.abs(fileDistance) < LIMIT_DISTANCE
                && Math.abs(rankDistance) < LIMIT_DISTANCE;
    }

    private boolean isDiagonal(final Direction direction) {
        return (super.isSameColor(Color.WHITE) &&
                (direction == Direction.NORTH_EAST || direction == Direction.NORTH_WEST)) ||
                (super.isSameColor(Color.BLACK) &&
                        (direction == Direction.SOUTH_EAST || direction == Direction.SOUTH_WEST));
    }
}
