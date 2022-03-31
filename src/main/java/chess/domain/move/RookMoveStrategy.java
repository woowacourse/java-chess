package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public final class RookMoveStrategy extends LinearMoveStrategy {

    private static final String UNSUPPORTED_MOVE_PATTERN_MESSAGE = "[ERROR] 룩에서 지원하지 않는 이동전략입니다.";

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = new Distance(source, target);
        if (!isRookMovePattern(distance)) {
            return false;
        }
        final Position smallerPosition = source.compareSmaller(target);
        if (distance.isHorizontalMovement() && isPieceExistWhenHorizon(board, smallerPosition, distance)) {
            return false;
        }
        if (distance.isVerticalMovement() && isPieceExistWhenVertical(board, smallerPosition, distance)) {
            return false;
        }
        return isMovableToTarget(board.getPiece(target), board.getColorOfPiece(source));
    }

    private boolean isRookMovePattern(final Distance distance) {
        return distance.isHorizontalMovement() || distance.isVerticalMovement();
    }

    @Override
    protected boolean isPieceExistWhenPositiveDiagonal(final Board board,
                                                       final Position smallerPosition,
                                                       final Distance distance) {
        throw new UnsupportedOperationException(UNSUPPORTED_MOVE_PATTERN_MESSAGE);
    }

    @Override
    protected boolean isPieceExistWhenNegativeDiagonal(final Board board,
                                                       final Position smallerPosition,
                                                       final Distance distance) {
        throw new UnsupportedOperationException(UNSUPPORTED_MOVE_PATTERN_MESSAGE);
    }
}
