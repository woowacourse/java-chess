package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Row;

public class Queen extends Piece {

    private static final int ROW = 1;
    private static final int COLUMN = 0;

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final String start, final String end) {
        validateMove(start, end);
    }

    private void validateMove(final String start, final String end) {
        int subRow = Row.subPositionFromArrivePosition(start.charAt(ROW), end.charAt(ROW));
        int subCol = Col.subPositionFromArrivePosition(start.charAt(COLUMN), end.charAt(COLUMN));

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
