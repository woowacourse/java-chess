package chess.domain.piece;

public enum Color {
    WHITE,
    BLACK,
    EMPTY,
    ;

    public boolean is(final Color color) {
        if (color == EMPTY) {
            return false;
        }
        return this == color;
    }

    public boolean isOpponent(final Color color) {
        if (color == EMPTY) {
            return false;
        }
        return this != color;
    }

    public Color nextTurn() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
