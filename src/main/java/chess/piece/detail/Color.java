package chess.piece.detail;

public enum Color {
    BLACK, WHITE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean hasSameColor(final Color color) {
        return this == color;
    }

}
