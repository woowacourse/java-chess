package chess.domain;

public enum Color {
    WHITE,
    BLACK,
    NONE,
    ;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public Color getOpposite() {
        if (isBlack()) {
            return WHITE;
        }

        return BLACK;
    }

    public boolean isSame(Color color) {
        return this == color;
    }
}
