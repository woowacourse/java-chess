package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Row;

public class Rook extends Piece {

    private static final int ROW = 1;
    private static final int COLUMN = 0;

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final String start, final String end) {
        validateMove(start, end);
    }

    private void validateMove(final String start, final String end) {
        int subRow = Row.subPositionFromArrivePosition(start.charAt(ROW), end.charAt(ROW));
        int subCol = Col.subPositionFromArrivePosition(start.charAt(COLUMN), end.charAt(COLUMN));

        if (subRow != 0 && subCol != 0) {
            throw new IllegalArgumentException("올바르지 않은 위치로 이동할 수 없습니다.");
        }
    }
}
