package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.function.Predicate;

public class BishopMoveCondition extends MoveCondition {

    @Override
    public boolean isSatisfyBy(final Board board, final Piece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isRightMovePath(piece, target) &&
                isNotExistObstacleOnPath(board, piece, target) &&
                isNotChessPieceOutOfBoard(board, target);
    }

    private boolean isRightMovePath(final Piece piece, final Position target) {
        return Math.abs(piece.getColumn() - target.getColumn())
                == Math.abs(piece.getRow() - target.getRow());
    }

    private boolean isNotExistObstacleOnPath(Board board, Piece piece, Position target) {
        return board.isNoneMatchByFilteredPieces(isExistInMoveArea(piece, target),
                hasSameGradientWithSourceAndTarget(piece, target));
    }

    private Predicate<Piece> hasSameGradientWithSourceAndTarget(final Piece piece, final Position target) {
        return selectedPiece ->
                piece.calculateGradient(target) ==
                        piece.calculateGradient(selectedPiece.getPosition());
    }

    private Predicate<Piece> isExistInMoveArea(final Piece piece, final Position target) {
        int maxCol = Math.max(piece.getColumn(), target.getColumn());
        int minCol = Math.min(piece.getColumn(), target.getColumn());
        int maxRow = Math.max(piece.getRow(), target.getRow());
        int minRow = Math.min(piece.getRow(), target.getRow());

        return pieceOnBoard ->
                minCol <= pieceOnBoard.getColumn() &&
                        pieceOnBoard.getColumn() <= maxCol &&
                        minRow <= pieceOnBoard.getRow() &&
                        pieceOnBoard.getRow() <= maxRow;
    }

}
