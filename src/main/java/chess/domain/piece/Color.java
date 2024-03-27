package chess.domain.piece;

public enum Color {
    BLACK,
    WHITE,
    NONE,
    ;

    public Color opposite() {
        if (this == Color.BLACK) {
            return Color.WHITE;
        }
        if (this == Color.WHITE) {
            return Color.BLACK;
        }
        return Color.NONE;
    }
}
