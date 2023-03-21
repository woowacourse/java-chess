package chess.domain.piece;

public enum Color {

    BLACK,
    WHITE,
    ;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isSame(final Color color) {
        return this == color;
    }

    public Color reverse() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
