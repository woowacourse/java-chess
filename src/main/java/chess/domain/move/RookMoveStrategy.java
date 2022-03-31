package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public final class RookMoveStrategy extends LinearMoveStrategy {

    private static final String UNSUPPORTED_MOVE_PATTERN_MESSAGE = "[ERROR] 룩에서 지원하지 않는 이동전략입니다.";

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Movement movement = new Movement(source, target);
        if (!isRookMovePattern(movement)) {
            return false;
        }
        final Position smallerPosition = source.compareSmaller(target);
        if (movement.isHorizontal() && isPieceExistAmongHorizon(board, smallerPosition, movement)) {
            return false;
        }
        if (movement.isVertical() && isPieceExistAmongVertical(board, smallerPosition, movement)) {
            return false;
        }
        return isMovableToTarget(board.getPiece(target), board.getTeamOfPiece(source));
    }

    private boolean isRookMovePattern(final Movement movement) {
        return movement.isHorizontal() || movement.isVertical();
    }

    @Override
    protected boolean isPieceExistAmongPositiveDiagonal(final Board board,
                                                        final Position smallerPosition,
                                                        final Movement movement) {
        throw new UnsupportedOperationException(UNSUPPORTED_MOVE_PATTERN_MESSAGE);
    }

    @Override
    protected boolean isPieceExistAmongNegativeDiagonal(final Board board,
                                                        final Position smallerPosition,
                                                        final Movement movement) {
        throw new UnsupportedOperationException(UNSUPPORTED_MOVE_PATTERN_MESSAGE);
    }
}
