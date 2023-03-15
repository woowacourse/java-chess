package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Row;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Name name) {
        super(name);
    }

    @Override
    public void canMove(final String start, final String end) {
        validateMove(start, end);
    }

    private void validateMove(final String start, final String end) {
        List<List<Integer>> possibleSubPosition = List.of(List.of(1, 2), List.of(2, 1));

        int absOfRow = Math.abs(Row.subPositionFromArrivePosition(start.charAt(1), end.charAt(1)));
        int absOfCol = Math.abs(Col.subPositionFromArrivePosition(start.charAt(0), end.charAt(0)));
        List<Integer> newPosition = List.of(absOfCol, absOfRow);

        if (!possibleSubPosition.contains(newPosition)) {
            throw new IllegalArgumentException("Knight의 이동 범위가 올바르지 않습니다.");
        }
    }
}
