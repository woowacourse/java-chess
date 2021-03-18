package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;

public abstract class NoJumpMoveStrategy extends BasicMoveStrategy {

    protected void checkClearPath(Position source, Position target, MoveDirection moveDirection, Board board) {
        int moveNumber = calculateMoveNumber(source, target);
        Position pathPosition = source;
        int xVector = moveDirection.getXVector();
        int yVector = moveDirection.getYVector();

        for (int i = 0; i < moveNumber - 1; i++) {
            pathPosition = Position.of(pathPosition.getXPosition().moveUnit(xVector),
                pathPosition.getYPosition().moveUnit(yVector));
            checkIfClear(board, pathPosition);
        }
    }

    private int calculateMoveNumber(Position source, Position target) {
        int verticalMoveNumber = source.computeVerticalDistance(target);
        if (verticalMoveNumber != 0) {
            return Math.abs(verticalMoveNumber);
        }
        return Math.abs(source.computeHorizontalDistance(target));
    }

    private void checkIfClear(Board board, Position pathPosition) {
        if (!board.checkPieceAtPosition(pathPosition).equals(VOID_PIECE)) {
            throw new InvalidMoveException(Piece.UNABLE_CROSS_MESSAGE);
        }
    }

    protected boolean isLineMove(Position source, Position target) {
        return (source.computeHorizontalDistance(target) == 0 ||
            source.computeVerticalDistance(target) == 0);
    }

    protected boolean isDiagonalMove(Position source, Position target) {
        return (Math.abs(source.computeHorizontalDistance(target))
            == Math.abs(source.computeVerticalDistance(target)));
    }
}
