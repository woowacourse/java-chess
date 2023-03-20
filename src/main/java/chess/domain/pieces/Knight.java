package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Row;
import java.util.List;

public class Knight extends Piece {

    private static final int ROW = 1;
    private static final int COLUMN = 0;

    public Knight(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final String start, final String end) {
        List<List<Integer>> possibleSubPosition = List.of(List.of(1, 2), List.of(2, 1));

        int absOfRow = Math.abs(Row.subPositionFromArrivePosition(start.charAt(ROW), end.charAt(ROW)));
        int absOfCol = Math.abs(Col.subPositionFromArrivePosition(start.charAt(COLUMN), end.charAt(COLUMN)));
        List<Integer> newPosition = List.of(absOfCol, absOfRow);

        if (!possibleSubPosition.contains(newPosition)) {
            throw new IllegalArgumentException("Knight의 이동 범위가 올바르지 않습니다.");
        }
    }
}
