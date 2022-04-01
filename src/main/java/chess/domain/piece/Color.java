package chess.domain.piece;

public enum Color {
    BLACK,
    WHITE;

    public Color opposite() {
        if (this == BLACK) {
            return WHITE;
        }

        return BLACK;
    }
}
