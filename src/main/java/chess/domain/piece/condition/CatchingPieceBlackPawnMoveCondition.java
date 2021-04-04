package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class CatchingPieceBlackPawnMoveCondition extends MoveCondition {

    @Override
    public boolean isSatisfyBy(final Board board, final Piece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isRightMovePath(piece, target) &&
                isEnemyExist(board, target) &&
                isNotSameColorOnTarget(board, piece, target) &&
                isNotChessPieceOutOfBoard(board, target);
    }

    private boolean isRightMovePath(final Piece piece, final Position target) {
        return target.equals(new Position(piece.getRow() + 1, piece.getColumn() - 1)) ||
                target.equals(new Position(piece.getRow() + 1, piece.getColumn() + 1));
    }

    private boolean isEnemyExist(final Board board, final Position target) {
        return board.getPieces().stream()
                .anyMatch(
                        piece -> piece.isSamePosition(target) &&
                                piece.isSameColor(Color.WHITE)
                );
    }

}
