package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public final class RookMoveStrategy extends LinearMoveStrategy {

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = Distance.of(source, target);

        if (!distance.isHorizontalMovement() && !distance.isVerticalMovement()) {
            return false;
        }

        final Position smallerPosition = source.compareSmaller(target);
        if (distance.isHorizontalMovement() && countPiecesWhenHorizon(board, smallerPosition, distance) > 0) {
            return false;
        }
        if (distance.isVerticalMovement() && countPiecesWhenVertical(board, smallerPosition, distance) > 0) {
            return false;
        }

        return isTargetPositionMovable(board.getPiece(target), board.getPiece(source).getColor());
    }
}
