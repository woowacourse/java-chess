package chess.domain.pieces;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.exception.PieceMessage;

public class Bishop extends Piece {

    private static final double SCORE_OF_BISHOP = 3;

    public Bishop(final Name name) {
        super(name);
        this.score = new Score(SCORE_OF_BISHOP);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        int absSubRow = Math.abs(Row.subPositionFromArrivePosition(start.getRow(), end.getRow()));
        int absSubCol = Math.abs(Column.subPositionFromArrivePosition(start.getCol(), end.getCol()));

        if (absSubCol != absSubRow) {
            throw new IllegalArgumentException(PieceMessage.BISHOP_INVALID_MOVE.getMessage());
        }
    }
}
