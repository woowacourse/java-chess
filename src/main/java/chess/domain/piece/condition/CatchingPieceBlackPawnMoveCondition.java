package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;

public class CatchingPieceBlackPawnMoveCondition extends MoveCondition {

    @Override
    public boolean isSatisfiedBy(final Board board, final ChessPiece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isRightMovePath(piece, target) &&
                isThereAnyEnemies(board, piece, target) &&
                isNotTheChessPieceGoOffTheBoard(target);
    }

    private boolean isRightMovePath(final ChessPiece piece, final Position target) {
        return target.equals(new Position(piece.getRow() + 1, piece.getColumn() - 1)) ||
                target.equals(new Position(piece.getRow() + 1, piece.getColumn() + 1));
    }

    private boolean isThereAnyEnemies(final Board board, final ChessPiece piece, final Position target) {
        return board.getWhitePieces().stream()
                .anyMatch(
                        pieceOnBoard -> pieceOnBoard.isSamePosition(target) &&
                                !pieceOnBoard.isSameColor(piece)
                );
    }

}
