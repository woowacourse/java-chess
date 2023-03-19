package chess.domain.pieces;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.exception.PieceMessage;
import java.util.List;

public class King extends Piece {

    private static final double SCORE_OF_KING = 0;

    public King(final Name name) {
        super(name);
        this.score = new Score(SCORE_OF_KING);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        int absSubRow = Math.abs(Row.subPositionFromArrivePosition(start.getRow(), end.getRow()));
        int absSubCol = Math.abs(Column.subPositionFromArrivePosition(start.getCol(), end.getCol()));

        List<Integer> nextDirection = List.of(absSubRow, absSubCol);

        validateMove(nextDirection);
    }

    private void validateMove(final List<Integer> nextDirection) {
        List<List<Integer>> possibleDirectionOfKing = List.of(List.of(1, 0), List.of(0, 1), List.of(1, 1));

        if (!possibleDirectionOfKing.contains(nextDirection)) {
            throw new IllegalArgumentException(PieceMessage.KING_INVALID_MOVE.getMessage());
        }
    }

    @Override
    public boolean isKing() {
        return this.name.isKing();
    }
}
