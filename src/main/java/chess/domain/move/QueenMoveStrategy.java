package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class QueenMoveStrategy extends LinearMoveStrategy {

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = Distance.of(source, target);

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

        return isTargetPositionMovable(board.getPiece(target), board.getPiece(source).getColor());
    }

    private boolean isQueenMovePattern(final Distance distance) {
        return distance.isHorizontalMovement()
                || distance.isVerticalMovement()
                || distance.isPositiveDiagonal()
                || distance.isNegativeDiagonal();
    }
}
