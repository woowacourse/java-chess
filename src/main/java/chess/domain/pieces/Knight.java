package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;
import java.util.List;

public class Knight extends Piece {

    private static final int ROW = 1;
    private static final int COLUMN = 0;

    public Knight(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final Position source, final Position destination) {
        List<List<Integer>> possibleSubPosition = List.of(List.of(1, 2), List.of(2, 1));

        int absOfRow = Math.abs(destination.calculateDistanceOfRow(source));
        int absOfCol = Math.abs(destination.calculateDistanceOfCol(source));
        List<Integer> newPosition = List.of(absOfCol, absOfRow);

        if (!possibleSubPosition.contains(newPosition)) {
            throw new IllegalArgumentException("Knight의 이동 범위가 올바르지 않습니다.");
        }
    }
}
