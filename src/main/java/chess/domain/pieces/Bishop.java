package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Row;

public class Bishop extends Piece {

    private static final int ROW = 1;
    private static final int COLUMN = 0;

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final String start, final String end) {
        int absSubRow = Math.abs(Row.subPositionFromArrivePosition(start.charAt(ROW), end.charAt(ROW)));
        int absSubCol = Math.abs(Col.subPositionFromArrivePosition(start.charAt(COLUMN), end.charAt(COLUMN)));

        if (!(absSubCol == absSubRow)) {
            throw new IllegalArgumentException("Bishop의 이동 범위가 올바르지 않습니다.");
        }
    }
}
