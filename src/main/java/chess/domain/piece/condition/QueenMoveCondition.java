package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.io.PipedInputStream;
import java.util.List;

public class QueenMoveCondition extends MoveCondition{
    @Override
    public boolean isSatisfyBy(final Board board, final Piece piece, final Position target) {
        return !piece.isSamePosition(target) && (
                isRightXCondition(piece, target) || isCrossMovable(piece,target)
        ) && validateObstacleOnCrossPath(board, piece, target) && validateObstacleOnXPath(board, piece, target) &&
                validateChessPieceOutOfBoard(board, target);
    }

    private boolean isCrossMovable(final Piece piece, final Position target) {
        return piece.getRow() == target.getRow() || piece.getColumn() == target.getColumn();
    }

    private boolean isRightXCondition(final Piece piece, final Position target) { //todo: 메서드명 리팩토링
        return Math.abs(piece.getColumn() - target.getColumn())
                        == Math.abs(piece.getRow() - target.getRow());
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

    private boolean validateObstacleOnXPath(Board board, Piece piece, Position target) {
        List<Piece> pieces = board.getPieces();

        int maxCol = Math.max(piece.getColumn(), target.getColumn());
        int minCol = Math.min(piece.getColumn(), target.getColumn());
        int maxRow = Math.max(piece.getRow(), target.getRow());
        int minRow = Math.min(piece.getRow(), target.getRow());

        return pieces.stream()
                .filter(p ->
                        minCol <= p.getColumn() && p.getColumn() <= maxCol &&
                                minRow <= p.getRow() && p.getRow() <= maxRow
                ).noneMatch(p -> new Position(piece.getRow(), piece.getColumn()).calculateGradient(target) ==
                        new Position(piece.getRow(), piece.getColumn()).calculateGradient(new Position(p.getRow(), p.getColumn())));
    }

}
