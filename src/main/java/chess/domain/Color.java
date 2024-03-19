package chess.domain;

public enum Color {
    WHITE,
    BLACK,
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
}
