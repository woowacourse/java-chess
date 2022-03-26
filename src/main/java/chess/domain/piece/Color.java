package chess.domain.piece;

public enum Color {
    BLACK,
    WHITE,
    NONE;

    public Color switchColor() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
