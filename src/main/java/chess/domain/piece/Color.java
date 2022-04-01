package chess.domain.piece;

public enum Color {
    BLACK,
    WHITE,
    EMPTY;

    public Color opposite() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }

        return EMPTY;
    }
}
