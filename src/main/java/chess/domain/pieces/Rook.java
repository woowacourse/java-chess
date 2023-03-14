package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class Rook extends Piece {

    public Rook(final Position position) {
        super(position);
    }

    @Override
    public void move(final String position) {
        validate(position);
        this.position = new Position(position);
    }

    private void validate(final String position) {
        validateMovePosition(position);
    }

    private void validateMovePosition(final String position) {
        Row myRow = this.position.row;
        Col myCol = this.position.col;

        int rowDiff = myRow.calculateSubstitutionFromArrivalPosition(position.charAt(1));
        int colDiff = myCol.calculateSubstitutionFromArrivalPosition(position.charAt(0));

        if (colDiff != 0 && rowDiff != 0) {
            throw new IllegalArgumentException("올바르지 않은 위치로 이동할 수 없습니다.");
        }
    }
}
