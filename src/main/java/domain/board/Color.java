package domain.board;

public enum Color {
    BLACK,
    WHITE,
    NONE;

    public Color reverse() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isSameColor(final Color color) {
        return this == color;
    }
}
