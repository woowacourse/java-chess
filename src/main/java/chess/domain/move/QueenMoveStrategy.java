package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public final class  QueenMoveStrategy extends LinearMoveStrategy {

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Movement movement = new Movement(source, target);
        if (!isQueenMovePattern(movement)) {
            return false;
        }
        final Position smallerPosition = source.compareSmaller(target);
        if (movement.isHorizontal() && isPieceExistAmongHorizon(board, smallerPosition, movement)) {
            return false;
        }
        if (movement.isVertical() && isPieceExistAmongVertical(board, smallerPosition, movement)) {
            return false;
        }
        if (movement.isPositiveDiagonal() && isPieceExistAmongPositiveDiagonal(board, smallerPosition, movement)) {
            return false;
        }
        if (movement.isNegativeDiagonal() && isPieceExistAmongNegativeDiagonal(board, smallerPosition, movement)) {
            return false;
        }
        return isMovableToTarget(board.getPiece(target), board.getTeamOfPiece(source));
    }

    private boolean isQueenMovePattern(final Movement movement) {
        return movement.isHorizontal() ||
                movement.isVertical() ||
                movement.isPositiveDiagonal() ||
                movement.isNegativeDiagonal();
    }
}
