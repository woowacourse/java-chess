package chess.domain;

public enum Color {
    WHITE,
    BLACK,
    NONE;

    public boolean isSame(Color color) {
        return this == color;
    }

    public Color reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
