package domain.piece.info;

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
}
