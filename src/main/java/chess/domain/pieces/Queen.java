package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class Queen extends Piece {

    private static final int ROW = 1;
    private static final int COLUMN = 0;

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final Position source, final Position destination) {
        validateMove(source, destination);
    }

    private void validateMove(final Position source, final Position destination) {
        int subRow = destination.calculateDistanceOfRow(source);
        int subCol = destination.calculateDistanceOfCol(source);

        if (!validateMoveLikeBishop(subRow, subCol) && !validateMoveLikeRook(subRow, subCol)) {
            throw new IllegalArgumentException("Queen의 이동 범위가 올바르지 않습니다.");
        }
    }

    private boolean validateMoveLikeBishop(final int subRow, final int subCol) {
        return Math.abs(subCol) == Math.abs(subRow);
    }

    private boolean validateMoveLikeRook(final int subRow, final int subCol) {
        return subRow == 0 || subCol == 0;
    }
}
