package chess.domain.piece;

public enum Color {

    BLACK,
    WHITE;

    public boolean isDifferentColor(Color color) {
        return this != color;
    }

    public boolean isSameColor(Color color) {
        return this == color;
    }

    public Color opposite() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
