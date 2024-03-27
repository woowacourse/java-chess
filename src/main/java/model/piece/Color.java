package model.piece;

public enum Color {
    BLACK, WHITE, UN_COLORED;

    public Color opponent() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
