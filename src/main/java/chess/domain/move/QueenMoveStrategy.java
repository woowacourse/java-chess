package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public final class QueenMoveStrategy extends LinearMoveStrategy {

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = new Distance(source, target);
        if (!isQueenMovePattern(distance)) {
            return false;
        }
        final Position smallerPosition = source.compareSmaller(target);
        if (distance.isHorizontalMovement() && isPieceExistWhenHorizon(board, smallerPosition, distance)) {
            return false;
        }
        if (distance.isVerticalMovement() && isPieceExistWhenVertical(board, smallerPosition, distance)) {
            return false;
        }
        if (distance.isPositiveDiagonal() && isPieceExistWhenPositiveDiagonal(board, smallerPosition, distance)) {
            return false;
        }
        if (distance.isNegativeDiagonal() && isPieceExistWhenNegativeDiagonal(board, smallerPosition, distance)) {
            return false;
        }
        return isMovableToTarget(board.getPiece(target), board.getColorOfPiece(source));
    }

    private boolean isQueenMovePattern(final Distance distance) {
        return distance.isHorizontalMovement() ||
                distance.isVerticalMovement() ||
                distance.isPositiveDiagonal() ||
                distance.isNegativeDiagonal();
    }
}
