package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;
import java.util.List;

public class King extends Piece{

    public King(final Name name) {
        super(name);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        List<List<Integer>> possiblePosition = List.of(List.of(1,0), List.of(0,1), List.of(1,1));

        int absSubRow = Math.abs(Row.subPositionFromArrivePosition(start.getRow(), end.getRow()));
        int absSubCol = Math.abs(Col.subPositionFromArrivePosition(start.getCol(), end.getCol()));

        List<Integer> subPosition = List.of(absSubRow, absSubCol);

        if (!possiblePosition.contains(subPosition)) {
            throw new IllegalArgumentException("올바른 위치가 아닙니다.");
        }
    }
}
