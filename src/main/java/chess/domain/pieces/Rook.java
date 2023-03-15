package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Row;

public class Rook extends Piece {

    public Rook(final Name name) {
        super(name);
    }

    @Override
    public void canMove(final String start, final String end) {
        validateMove(start, end);
    }

    private void validateMove(final String start, final String end) {
        int subRow = Row.subPositionFromArrivePosition(start.charAt(1), end.charAt(1));
        int subCol = Col.subPositionFromArrivePosition(start.charAt(0), end.charAt(0));

        if (subRow != 0 && subCol != 0) {
            throw new IllegalArgumentException("올바르지 않은 위치로 이동할 수 없습니다.");
        }
    }

}
