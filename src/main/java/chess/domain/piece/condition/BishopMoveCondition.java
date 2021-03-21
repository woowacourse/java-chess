package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;

import java.util.List;
import java.util.function.Predicate;

public class BishopMoveCondition extends MoveCondition {

    @Override
    public boolean isSatisfyBy(final Board board, final ChessPiece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isRightMovePath(piece, target) &&
                isNotExistObstacleOnPath(board, piece, target) &&
                isNotChessPieceOutOfBoard(target);
    }

    private boolean isRightMovePath(final ChessPiece piece, final Position target) {
        return Math.abs(piece.getColumn() - target.getColumn())
                == Math.abs(piece.getRow() - target.getRow());
    }

    private boolean isNotExistObstacleOnPath(Board board, ChessPiece piece, Position target) {
        List<ChessPiece> pieces = board.getAllPieces();

        return pieces.stream()
                .filter(isExistInMoveArea(piece, target))
                .noneMatch(hasSameGradientWithSourceAndTarget(piece, target));
    }

    private Predicate<ChessPiece> hasSameGradientWithSourceAndTarget(final ChessPiece piece, final Position target) {
        return selectedPiece ->
                piece.getPosition().calculateGradient(target) ==
                        piece.getPosition().calculateGradient(selectedPiece.getPosition());
    }

    private Predicate<ChessPiece> isExistInMoveArea(final ChessPiece piece, final Position target) {
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
