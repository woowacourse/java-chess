package chess.domain.piece;

import chess.domain.position.Row;

public enum Color {
    BLACK(-1),
    WHITE(1);

    private final int moveUnit;

    Color(final int moveUnit) {
        this.moveUnit = moveUnit;
    }

    public int moveUnit() {
        return this.moveUnit;
    }

    public Row initPawnRow() {
        if (this == BLACK) {
            return Row.SEVEN;
        }
        return Row.TWO;
    }
}
