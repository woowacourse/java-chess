package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Row;

public class Pawn extends Piece {

    private boolean isFirstMove;

    public Pawn(final Name name) {
        super(name);
        this.isFirstMove = true;
    }

    @Override
    public void canMove(final String start, final String end) {
        if (!validatePosition(start, end)) {
            throw new IllegalArgumentException("Pawn의 움직임 범위가 올바르지 않습니다.");
        }
    }

    private boolean validatePosition(final String start, final String end) {
        int substitutionOfRow = Row.subPositionFromArrivePosition(start.charAt(1), end.charAt(1));
        int substitutionOfCol = Col.subPositionFromArrivePosition(start.charAt(0), end.charAt(0));

        if (this.isFirstMove) {
            this.isFirstMove = false;
            return canMoveAtFirst(substitutionOfRow, substitutionOfCol);
        }
        return canMoveAfterFirst(substitutionOfRow, substitutionOfCol);
    }

    private boolean canMoveAtFirst(final int substitutionOfRow, final int substitutionOfCol) {
        if (isNameLowerCase()) {
            return substitutionOfCol == 0 && (substitutionOfRow == 1 || substitutionOfRow == 2);
        }
        return substitutionOfCol == 0 && (substitutionOfRow == -1 || substitutionOfRow == -2);
    }

    private boolean canMoveAfterFirst(final int substitutionOfRow, final int substitutionOfCol) {
        if (isNameLowerCase()) {
            return substitutionOfCol == 0 && substitutionOfRow == 1;
        }
        return substitutionOfCol == 0 && substitutionOfRow == -1;
    }


}
