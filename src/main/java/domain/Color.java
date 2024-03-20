package domain;

public enum Color {
    WHITE, BLACK;

    public Color toggle() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
