package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;

import java.util.List;
import java.util.function.Predicate;

public class QueenMoveCondition extends MoveCondition {

    @Override
    public boolean isSatisfiedBy(final Board board, final ChessPiece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isMovablePath(piece, target) &&
                isThereNoObstacleInThePath(board, piece, target) &&
                isThereNoObstacleOfTheSameColorAtTarget(board, piece, target) &&
                isNotTheChessPieceGoOffTheBoard(target);
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

    private boolean isThereNoObstacleInThePath(Board board, ChessPiece piece, Position target) {
        if (Math.abs(piece.getPosition().calculateGradient(target)) == 1) {
            return isThereNoObstacleOnXPath(board, piece, target);
        }

        return isThereNoObstacleOnCrossPath(board, piece, target);
    }

    private boolean isThereNoObstacleOnCrossPath(Board board, ChessPiece piece, Position target) {
        return board.getAllPieces().stream()
                .filter(isThereNoObstacleOnCrossPath(piece, target))
                .peek(p -> System.out.println(p.getRow() + ":" + p.getColumn()))
                .noneMatch(isThereNoObstacleOnCrossPath(piece, target));
    }

    private Predicate<ChessPiece> isThereNoObstacleOnCrossPath(final ChessPiece piece, final Position target) {
        int maxCol = Math.max(piece.getColumn(), target.getColumn());
        int minCol = Math.min(piece.getColumn(), target.getColumn());
        int maxRow = Math.max(piece.getRow(), target.getRow());
        int minRow = Math.min(piece.getRow(), target.getRow());

        if (piece.getColumn() == target.getColumn()) {
            return pieceOnBoard -> pieceOnBoard.getColumn() == piece.getColumn() &&
                    minRow < pieceOnBoard.getRow() && pieceOnBoard.getRow() < maxRow;
        }

        if (piece.getRow() == target.getRow()) {
            return pieceOnBoard -> pieceOnBoard.getRow() == piece.getRow() &&
                    minCol < pieceOnBoard.getColumn() && pieceOnBoard.getColumn() < maxCol;
        }

        return pieceOnBoard -> false;
    }

    private boolean isThereNoObstacleOfTheSameColorAtTarget(Board board, ChessPiece piece, Position target) {
        return board.getWhitePieces().stream()
                .noneMatch(
                        pieceOnBoard -> pieceOnBoard.isSamePosition(target) &&
                                pieceOnBoard.isSameColor(piece)
                );
    }


    private boolean isThereNoObstacleOnXPath(Board board, ChessPiece piece, Position target) {
        List<ChessPiece> pieces = board.getAllPieces();

        return pieces.stream()
                .filter(isTherePieceInTheMovementArea(piece, target))
                .noneMatch(hasSameGradientWithSourceAndTarget(piece, target));
    }

    private Predicate<ChessPiece> isTherePieceInTheMovementArea(ChessPiece piece, Position target) {
        int maxCol = Math.max(piece.getColumn(), target.getColumn());
        int minCol = Math.min(piece.getColumn(), target.getColumn());
        int maxRow = Math.max(piece.getRow(), target.getRow());
        int minRow = Math.min(piece.getRow(), target.getRow());

        return pieceOnBoard -> minCol < pieceOnBoard.getColumn() &&
                pieceOnBoard.getColumn() < maxCol &&
                minRow < pieceOnBoard.getRow() &&
                pieceOnBoard.getRow() < maxRow;
    }

    private Predicate<ChessPiece> hasSameGradientWithSourceAndTarget(final ChessPiece piece, final Position target) {
        return selectedPiece ->
                piece.getPosition().calculateGradient(target) ==
                        piece.getPosition().calculateGradient(selectedPiece.getPosition());
    }

}
