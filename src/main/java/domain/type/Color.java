package domain.type;

public enum Color {
    WHITE, BLACK, NONE;

    public Color reverse() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return NONE;
    }
}
