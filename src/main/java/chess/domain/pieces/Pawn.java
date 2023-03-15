package chess.domain.pieces;

import chess.domain.board.Position;

public class Pawn extends Piece {

    private boolean isFirstMove;

    public Pawn(final Position position) {
        super(position);
        this.isFirstMove = true;
    }

    @Override
    public void move(final String position) {
        validate(position);
        this.position = new Position(position);
    }

    private void validate(final String position) {
        if (!validatePosition(position)) {
            throw new IllegalArgumentException("Pawn의 움직임 범위가 올바르지 않습니다.");
        }
    }

    private boolean validatePosition(final String position) {
        int substitutionOfRow = this.position.subRowFromArriveRow(position);
        int substitutionOfCol = this.position.subColFromArriveCol(position);

        if (this.isFirstMove) {
            this.isFirstMove = false;
            return canMoveAtFirst(substitutionOfRow, substitutionOfCol);
        }
        return substitutionOfCol == 0 && substitutionOfRow == 1;
    }

    private boolean canMoveAtFirst(final int substitutionOfRow, final int substitutionOfCol) {
        return substitutionOfCol == 0 && (substitutionOfRow == 1 || substitutionOfRow == 2);
    }


}
