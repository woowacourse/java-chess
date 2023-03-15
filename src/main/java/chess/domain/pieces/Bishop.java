package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Row;

public class Bishop extends Piece {

    public Bishop(final Name name) {
        super(name);
    }

    @Override
    public void canMove(final String start, final String end) {
        int absSubRow = Math.abs(Row.subPositionFromArrivePosition(start.charAt(1), end.charAt(1)));
        int absSubCol = Math.abs(Col.subPositionFromArrivePosition(start.charAt(0), end.charAt(0)));

        if (!(absSubCol == absSubRow)) {
            throw new IllegalArgumentException("Bishop의 이동 범위가 올바르지 않습니다.");
        }
    }
}
