package chess.domain.piece;

public enum Color {
    BLACK, WHITE;

    public Color change() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
