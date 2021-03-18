package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.List;

public class BishopMoveCondition extends MoveCondition {
    @Override
    public boolean isSatisfyBy(final Board board, final Piece piece, final Position target) {
        return !piece.equals(target) &&
                validateMovableTarget(piece, target) &&
                validateObstacleOnPath(board, piece, target) &&
                validateChessPieceOutOfBoard(board, target);
    }

    private boolean validateMovableTarget(final Piece piece, final Position target) {
        return Math.abs(piece.getColumn() - target.getColumn())
                == Math.abs(piece.getRow() - target.getRow());
    }

    private boolean validateObstacleOnPath(Board board, Piece piece, Position target) {
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
