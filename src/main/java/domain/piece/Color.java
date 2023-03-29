package domain.piece;

public enum Color {

    BLACK, WHITE;

    public Color reverse() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isSame(final Color color) {
        return this == color;
    }

    public boolean isDifferent(final Color color) {
        return this != color;
    }
}
