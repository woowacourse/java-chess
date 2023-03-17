package chess.domain.piece;

public enum Color {
    BLACK, WHITE;

    public Color change() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
