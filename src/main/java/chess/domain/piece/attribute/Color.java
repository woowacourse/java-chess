package chess.domain.piece.attribute;

public enum Color {
    BLACK,
    WHITE,
    NONE;

    public Color oppositeColor() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
