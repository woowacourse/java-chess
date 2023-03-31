package chess.domain;

public enum Color {
    BLACK,
    WHITE,
    EMPTY;

    public Color getOppositeColor() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return EMPTY;
    }
}
