package chess.model.piece;

public enum Color {
    WHITE,
    NONE,
    BLACK;

    public Color getOpposite() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }
}
