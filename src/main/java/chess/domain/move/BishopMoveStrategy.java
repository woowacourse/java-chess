package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class BishopMoveStrategy extends LinearMoveStrategy {

    private static final String UNSUPPORTED_MOVE_PATTERN_MESSAGE = "[ERROR] 비숍에서 지원하지 않는 이동전략입니다.";

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = new Distance(source, target);
        if (!isBishopMovePattern(distance)) {
            return false;
        }
        final Position smallerPosition = source.compareSmaller(target);
        if (distance.isPositiveDiagonal() && isPieceExistWhenPositiveDiagonal(board, smallerPosition, distance)) {
            return false;
        }
        if (distance.isNegativeDiagonal() && isPieceExistWhenNegativeDiagonal(board, smallerPosition, distance)) {
            return false;
        }
        return isMovableToTarget(board.getPiece(target), board.getColorOfPiece(source));
    }

    private boolean isBishopMovePattern(final Distance distance) {
        return distance.isPositiveDiagonal() || distance.isNegativeDiagonal();
    }

    @Override
    protected boolean isPieceExistWhenHorizon(final Board board,
                                              final Position smallerPosition,
                                              final Distance distance) {
        throw new UnsupportedOperationException(UNSUPPORTED_MOVE_PATTERN_MESSAGE);
    }

    @Override
    protected boolean isPieceExistWhenVertical(final Board board,
                                               final Position smallerPosition,
                                               final Distance distance) {
        throw new UnsupportedOperationException(UNSUPPORTED_MOVE_PATTERN_MESSAGE);
    }
}
