package chess.domain.pieces;

import chess.domain.board.Position;
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

    private boolean validatePosition(final String position) {
        List<List<Integer>> possibleSubPosition = List.of(List.of(1, 2), List.of(2, 1));

        int substitutionOfRow = this.position.subRowFromArriveRow(position);
        int substitutionOfCol = this.position.subColFromArriveCol(position);

        int absOfRow = Math.abs(substitutionOfRow);
        int absOfCol = Math.abs(substitutionOfCol);
        List<Integer> newPosition = List.of(absOfCol, absOfRow);

        return possibleSubPosition.contains(newPosition);
    }
}
