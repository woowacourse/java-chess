package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class RookMoveCondition extends MoveCondition{
    @Override
    public boolean isSatisfyBy(final Board board, final Piece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                (piece.getRow() == target.getRow() ||
                        piece.getColumn() == target.getColumn()
                ) && validateObstacleOnCrossPath(board, piece, target)&&
                validateChessPieceOutOfBoard(board, target);
    }

    private boolean validateObstacleOnCrossPath(Board board, Piece piece, Position target) {
        int maxCol = Math.max(piece.getColumn(), target.getColumn());
        int minCol = Math.min(piece.getColumn(), target.getColumn());
        int maxRow = Math.max(piece.getRow(), target.getRow());
        int minRow = Math.min(piece.getRow(), target.getRow());

        return board.getPieces().stream()
                .filter(p -> !p.equals(piece))
                .noneMatch(p ->
                        (minRow <= p.getRow()) && (p.getRow() <= maxRow) &&
                                (minCol <= p.getColumn() && p.getColumn() <= maxCol)
                );
    }
}
