package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;

import java.util.List;
import java.util.function.Predicate;

public class QueenMoveCondition extends MoveCondition {
    @Override
    public boolean isSatisfyBy(final Board board, final ChessPiece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isMovablePath(piece, target) &&
                (isNotExistObstacleOnCrossPath(board, piece, target) ||
                isNotExistObstacleOnXPath(board, piece, target)) &&
                isNotChessPieceOutOfBoard(target);
    }

    private boolean isMovablePath(final ChessPiece piece, final Position target) {
        return isRightXCondition(piece, target) || isCrossMovable(piece, target);
    }

    private boolean isCrossMovable(final ChessPiece piece, final Position target) {
        return piece.getRow() == target.getRow() || piece.getColumn() == target.getColumn();
    }

    private boolean isRightXCondition(final ChessPiece piece, final Position target) {
        return Math.abs(piece.getColumn() - target.getColumn())
                == Math.abs(piece.getRow() - target.getRow());
    }

    private boolean isNotExistObstacleOnCrossPath(Board board, ChessPiece piece, Position target) {
        int maxCol = Math.max(piece.getColumn(), target.getColumn());
        int minCol = Math.min(piece.getColumn(), target.getColumn());
        int maxRow = Math.max(piece.getRow(), target.getRow());
        int minRow = Math.min(piece.getRow(), target.getRow());

        return board.getAllPieces().stream()
                .filter(pieceOnBoard -> !pieceOnBoard.equals(piece))
                .noneMatch(pieceOnBoard ->
                        isExistObstacleOnCrossPath(maxCol, minCol, maxRow, minRow, pieceOnBoard)
                );
    }

    private boolean isExistObstacleOnCrossPath(final int maxCol,
                                               final int minCol,
                                               final int maxRow,
                                               final int minRow,
                                               final ChessPiece pieceOnBoard) {
        return (minRow <= pieceOnBoard.getRow()) && (pieceOnBoard.getRow() <= maxRow) &&
                (minCol <= pieceOnBoard.getColumn() && pieceOnBoard.getColumn() <= maxCol);
    }

    private boolean isNotExistObstacleOnXPath(Board board, ChessPiece piece, Position target) {
        List<ChessPiece> pieces = board.getAllPieces();

        return pieces.stream()
                .filter(isExistInMoveArea(piece, target))
                .noneMatch(hasSameGradientWithSourceAndTarget(piece, target));
    }

    private Predicate<ChessPiece> isExistInMoveArea(ChessPiece piece, Position target) {
        int maxCol = Math.max(piece.getColumn(), target.getColumn());
        int minCol = Math.min(piece.getColumn(), target.getColumn());
        int maxRow = Math.max(piece.getRow(), target.getRow());
        int minRow = Math.min(piece.getRow(), target.getRow());

        return pieceOnBoard -> minCol <= pieceOnBoard.getColumn() && pieceOnBoard.getColumn() <= maxCol &&
                minRow <= pieceOnBoard.getRow() && pieceOnBoard.getRow() <= maxRow;
    }

    private Predicate<ChessPiece> hasSameGradientWithSourceAndTarget(final ChessPiece piece, final Position target) {
        return selectedPiece ->
                piece.getPosition().calculateGradient(target) ==
                        piece.getPosition().calculateGradient(selectedPiece.getPosition());
    }

}
