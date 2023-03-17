package chess.domain.pieces;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.exception.PieceMessage;

public class Rook extends Piece {

    public Rook(final Name name) {
        super(name);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        validateMove(start, end);
    }

    private void validateMove(final Position start, final Position end) {
        int subRow = Row.subPositionFromArrivePosition(start.getRow(), end.getRow());
        int subCol = Column.subPositionFromArrivePosition(start.getCol(), end.getCol());

        if (subRow != 0 && subCol != 0) {
            throw new IllegalArgumentException(PieceMessage.ROOK_INVALID_MOVE.getMessage());
        }
    }
}
