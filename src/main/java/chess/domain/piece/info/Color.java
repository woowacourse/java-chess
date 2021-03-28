package chess.domain.piece.info;

public enum Color {
    WHITE,
    BLACK,
    NONE;

    public boolean isSame(Color color) {
        return this == color;
    }

    public Color reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
