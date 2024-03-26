package model.piece;

public enum Color {
    BLACK, WHITE, UN_COLORED;

    public Color next() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
