package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public final class BishopMoveStrategy extends LinearMoveStrategy {

    private static final String UNSUPPORTED_MOVE_PATTERN_MESSAGE = "[ERROR] 비숍에서 지원하지 않는 이동전략입니다.";

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Movement movement = new Movement(source, target);
        if (!isBishopMovePattern(movement)) {
            return false;
        }
        final Position smallerPosition = source.compareSmaller(target);
        if (movement.isPositiveDiagonal() && isPieceExistAmongPositiveDiagonal(board, smallerPosition, movement)) {
            return false;
        }
        if (movement.isNegativeDiagonal() && isPieceExistAmongNegativeDiagonal(board, smallerPosition, movement)) {
            return false;
        }
        return isMovableToTarget(board.getPiece(target), board.getTeamOfPiece(source));
    }

    private boolean isBishopMovePattern(final Movement movement) {
        return movement.isPositiveDiagonal() || movement.isNegativeDiagonal();
    }

    @Override
    protected boolean isPieceExistAmongHorizon(final Board board,
                                               final Position smallerPosition,
                                               final Movement movement) {
        throw new UnsupportedOperationException(UNSUPPORTED_MOVE_PATTERN_MESSAGE);
    }

    @Override
    protected boolean isPieceExistAmongVertical(final Board board,
                                                final Position smallerPosition,
                                                final Movement movement) {
        throw new UnsupportedOperationException(UNSUPPORTED_MOVE_PATTERN_MESSAGE);
    }
}
