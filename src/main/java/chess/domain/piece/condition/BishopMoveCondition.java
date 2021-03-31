package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;

import java.util.List;
import java.util.function.Predicate;

public class BishopMoveCondition extends MoveCondition {

    @Override
    public boolean isSatisfiedBy(final Board board, final ChessPiece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isRightMovePath(piece, target) &&
                isThereNoObstacleInThePath(board, piece, target) &&
                isThereNoObstacleOfTheSameColorAtTarget(board, piece, target) &&
                isNotTheChessPieceGoOffTheBoard(target);
    }

    private boolean isRightMovePath(final ChessPiece piece, final Position target) {
        return Math.abs(piece.getColumn() - target.getColumn())
                == Math.abs(piece.getRow() - target.getRow());
    }

    private boolean isThereNoObstacleInThePath(Board board, ChessPiece piece, Position target) {
        List<ChessPiece> pieces = board.getAllPieces();

        return pieces.stream()
                .filter(isTherePieceInTheMovementArea(piece, target))
                .filter(hasSameGradientWithSourceAndTarget(piece, target))
                .noneMatch(piece::isSameColor);
    }

    private boolean isThereNoObstacleOfTheSameColorAtTarget(Board board, ChessPiece piece, Position target) {
        return board.getWhitePieces().stream()
                .noneMatch(
                        pieceOnBoard -> pieceOnBoard.isSamePosition(target) &&
                                pieceOnBoard.isSameColor(piece)
                );
    }

    private Predicate<ChessPiece> hasSameGradientWithSourceAndTarget(final ChessPiece piece, final Position target) {
        return selectedPiece ->
                piece.getPosition().calculateGradient(target) ==
                        piece.getPosition().calculateGradient(selectedPiece.getPosition());
    }

    private Predicate<ChessPiece> isTherePieceInTheMovementArea(final ChessPiece piece, final Position target) {
        int maxCol = Math.max(piece.getColumn(), target.getColumn());
        int minCol = Math.min(piece.getColumn(), target.getColumn());
        int maxRow = Math.max(piece.getRow(), target.getRow());
        int minRow = Math.min(piece.getRow(), target.getRow());

        return pieceOnBoard ->
                minCol < pieceOnBoard.getColumn() &&
                        pieceOnBoard.getColumn() < maxCol &&
                        minRow < pieceOnBoard.getRow() &&
                        pieceOnBoard.getRow() < maxRow;
    }

}
