package chess.domain.piece;

public enum Color {
    BLACK, WHITE,;

    public boolean isWhite() {
        return this == WHITE;
    }

    public Color next() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
