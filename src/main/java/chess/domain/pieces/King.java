package chess.domain.pieces;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import java.util.List;

public class King extends Piece {

    public King(final Name name) {
        super(name);
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
            throw new IllegalArgumentException("King의 이동 범위가 올바르지 않습니다.");
        }
    }
}
