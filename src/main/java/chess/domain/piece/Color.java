package chess.domain.piece;

public enum Color {
    BLACK, WHITE;

    public Color not() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
