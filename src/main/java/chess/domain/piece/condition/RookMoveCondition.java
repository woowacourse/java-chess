package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;

import java.util.function.Predicate;

public class RookMoveCondition extends MoveCondition {

    @Override
    public boolean isSatisfyBy(final Board board, final ChessPiece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isRightMovable(piece, target) &&
                isNotExistObstacleOnCrossPath(board, piece, target) &&
                isNotExistSameColorObstacleOnTarget(board, piece, target) &&
                isNotChessPieceOutOfBoard(target);
    }

    private boolean isRightMovable(final ChessPiece piece, final Position target) {
        return piece.getRow() == target.getRow() ||
                piece.getColumn() == target.getColumn();
    }

    private boolean isNotExistObstacleOnCrossPath(Board board, ChessPiece piece, Position target) {
        return board.getAllPieces().stream()
                .filter(pieceOnBoard -> !pieceOnBoard.equals(piece))
                .noneMatch(isExistObstacleOnCrossPath(piece, target));
    }

    private boolean isNotExistSameColorObstacleOnTarget(Board board, ChessPiece piece, Position target) {
        return board.getWhitePieces().stream()
                .noneMatch(
                        pieceOnBoard -> pieceOnBoard.isSamePosition(target) &&
                                pieceOnBoard.isSameColor(piece)
                );
    }

    private Predicate<ChessPiece> isExistObstacleOnCrossPath(final ChessPiece piece, final Position target) {
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


        throw new IllegalArgumentException("잘못된 접근입니다.");
    }

}
