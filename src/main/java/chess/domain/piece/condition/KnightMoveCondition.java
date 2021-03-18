package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class KnightMoveCondition extends MoveCondition {
    @Override
    public boolean isSatisfyBy(final Board board, final Piece piece, final Position target) {
        int[] row = {3, 3, -3, -3, 1, -1, 1, -1};
        int[] col = {1, -1, 1, -1, 3, 3, -3, -3};

        boolean flag = false;
        for (int i = 0; i < row.length; i++) {
            int dr = piece.getRow() + row[i];
            int dc = piece.getColumn() + col[i];

            if (target.equals(new Position(dr, dc))) {
                flag = true;
            }
        }

        return !piece.isSamePosition(target) && flag && isExistSameColorPieceOnPath(board, piece, target) &&
                validateChessPieceOutOfBoard(board, target);
    }

    public boolean isExistSameColorPieceOnPath(Board board, Piece piece, Position target) {
        return board.getPieces().stream()
                .noneMatch(p -> p.isSamePosition(target) && piece.isSameColor(p.getColor()));
    }
}
