package model.piece;

public enum Color {
    BLACK, WHITE, UN_COLORED;

    public Color next() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
