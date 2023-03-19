package chess.domain.piece.property;

public enum Color {
    BLACK,
    WHITE,
    BLANK;

    public Color getOppositeColor() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return BLANK;
    }
}
