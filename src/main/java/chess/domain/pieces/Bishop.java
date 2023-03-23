package chess.domain.pieces;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.exception.PieceMessage;

public class Bishop extends Piece {


    public Bishop(final Name name) {
        super(name);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        int absSubRow = Math.abs(Row.subPositionFromArrivePosition(start.getRow(), end.getRow()));
        int absSubCol = Math.abs(Column.subPositionFromArrivePosition(start.getCol(), end.getCol()));

        if (!(absSubCol == absSubRow)) {
            throw new IllegalArgumentException(PieceMessage.BISHOP_INVALID_MOVE.getMessage());
        }
    }
}
