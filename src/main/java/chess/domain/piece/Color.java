package chess.domain.piece;

import chess.domain.location.Row;

public enum Color {
    BLACK,
    WHITE;

    public int moveUnit() {
        if (this == BLACK) {
            return -1;
        }
        return 1;
    }

    public Row initPawnRow() {
        if (this == BLACK) {
            return Row.SEVEN;
        }
        return Row.TWO;
    }

    public Row initGeneralRow() {
        if (this == BLACK) {
            return Row.EIGHT;
        }
        return Row.ONE;
    }
}
