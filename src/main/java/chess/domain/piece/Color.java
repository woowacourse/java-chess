package chess.domain.piece;

public enum Color {

    BLACK, WHITE, NONE;

    public Color getOpposite() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return this;
    }
}
