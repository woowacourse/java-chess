package chess.domain.pieces;

import chess.domain.board.Position;
import java.util.List;

public class King extends Piece{

    public King(final Position position) {
        super(position);
    }

    @Override
    public void move(final String position) {
        validateMove(position);
        this.position = new Position(position);
    }

    private void validateMove(final String position) {
        List<List<Integer>> possiblePosition = List.of(List.of(1,0), List.of(0,1), List.of(1,1));

        int absSubRow = Math.abs(this.position.subRowFromArriveRow(position));
        int absSubCol = Math.abs(this.position.subColFromArriveCol(position));
        List<Integer> subPosition = List.of(absSubRow, absSubCol);

        if (!possiblePosition.contains(subPosition)) {
            throw new IllegalArgumentException("올바른 위치가 아닙니다.");
        }
    }
}
