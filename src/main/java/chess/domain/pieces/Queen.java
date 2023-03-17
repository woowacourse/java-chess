package chess.domain.pieces;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.exception.PieceMessage;

public class Queen extends Piece {

    public Queen(final Name name) {
        super(name);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        validateMove(start, end);
    }

    private void validateMove(final Position start, final Position end) {
        int subRow = Row.subPositionFromArrivePosition(start.getRow(), end.getRow());
        int subCol = Column.subPositionFromArrivePosition(start.getCol(), end.getCol());

        if (!validateMoveLikeBishop(subRow, subCol) && !validateMoveLikeRook(subRow, subCol)) {
            throw new IllegalArgumentException(PieceMessage.QUEEN_INVALID_MOVE.getMessage());
        }
    }

    private boolean validateMoveLikeBishop(final int subRow, final int subCol) {
        return Math.abs(subCol) == Math.abs(subRow);
    }

    private boolean validateMoveLikeRook(final int subRow, final int subCol) {
        return subRow == 0 || subCol == 0;
    }
}
