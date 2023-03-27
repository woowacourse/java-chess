package chess.domain.side;

public enum Color {
    WHITE,
    BLACK,
    NOTHING;

    public Color findOpponent() {
        if (this == Color.NOTHING) {
            return Color.NOTHING;
        }
        if (this == Color.WHITE) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public boolean isOpponent(final Color target) {
        if (this == WHITE) {
            return target == BLACK;
        }
        if (this == BLACK) {
            return target == WHITE;
        }
        return false;
    }
}
