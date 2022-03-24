package chess.domain.piece;

public enum Color {

    BLACK, WHITE, NONE;

    public Color oppositeColor() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
