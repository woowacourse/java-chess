package chess.domain.piece;

public enum Color {
    BLACK,
    WHITE,
    EMPTY,
    ;

    public boolean isOpposite(Color color) {
        return this.getReverseColor() == color;
    }

    public boolean isSame(Color targetColor) {
        return this == targetColor;
    }

    public Color getReverseColor() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return EMPTY;
    }
}
