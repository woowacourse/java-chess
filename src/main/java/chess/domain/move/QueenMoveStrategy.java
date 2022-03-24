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
        if (distance.isHorizontalMovement() && countPiecesWhenHorizon(board, smallerPosition, distance) > 0) {
            return false;
        }
        if (distance.isVerticalMovement() && countPiecesWhenVertical(board, smallerPosition, distance) > 0) {
            return false;
        }
        if (distance.isPositiveDiagonal() && countPiecesWhenPositiveDiagonal(board, smallerPosition, distance) > 0) {
            return false;
        }
        if (distance.isNegativeDiagonal() && countPiecesWhenNegativeDiagonal(board, smallerPosition, distance) > 0) {
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
