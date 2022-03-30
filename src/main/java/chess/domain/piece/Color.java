package chess.domain.piece;

public enum Color {
    WHITE,
    BLACK;

    public Color invert() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
