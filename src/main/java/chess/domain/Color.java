package chess.domain;

public enum Color {
    BLACK,
    WHITE,
    NOTHING;

    public Color getOppositeColor() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return NOTHING;
    }
}
