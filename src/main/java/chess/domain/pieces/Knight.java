package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Position position) {
        super(position);
    }

    @Override
    public void move(final String position) {
        validate(position);
        this.position = new Position(position);
    }

    private void validate(final String position) {
        if (!validatePosition(position)) {
            throw new IllegalArgumentException("Knight의 이동 범위가 올바르지 않습니다.");
        }
    }

    private boolean validatePosition(final String arrivalPosition) {
        List<List<Integer>> possibleSubPosition = List.of(List.of(1, 2), List.of(2, 1));
        char arrivalCol = arrivalPosition.charAt(0);
        char arrivalRow = arrivalPosition.charAt(1);
        Row myRow = this.position.row;
        Col myCol = this.position.col;

        int absOfRow = Math.abs(myRow.calculateSubstitutionFromArrivalPosition(arrivalRow));
        int absOfCol = Math.abs(myCol.calculateSubstitutionFromArrivalPosition(arrivalCol));
        List<Integer> newPosition = List.of(absOfCol, absOfRow);

        return possibleSubPosition.contains(newPosition);
    }
}
