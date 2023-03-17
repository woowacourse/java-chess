package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class Bishop extends Piece {


    public Bishop(final Name name) {
        super(name);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        int absSubRow = Math.abs(Row.subPositionFromArrivePosition(start.getRow(), end.getRow()));
        int absSubCol = Math.abs(Col.subPositionFromArrivePosition(start.getCol(), end.getCol()));

        if (!(absSubCol == absSubRow)) {
            throw new IllegalArgumentException("Bishop의 이동 범위가 올바르지 않습니다.");
        }
    }
}
