package chess.domain.pieces;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.exception.PieceMessage;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Name name) {
        super(name);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        List<List<Integer>> possibleSubPosition = List.of(List.of(1, 2), List.of(2, 1));

        int absOfRow = Math.abs(Row.subPositionFromArrivePosition(start.getRow(), end.getRow()));
        int absOfCol = Math.abs(Column.subPositionFromArrivePosition(start.getCol(), end.getCol()));
        List<Integer> newPosition = List.of(absOfCol, absOfRow);

        if (!possibleSubPosition.contains(newPosition)) {
            throw new IllegalArgumentException(PieceMessage.KNIGHT_INVALID_MOVE.getMessage());
        }
    }
}
