package chess.domain;

public enum Color {
    BLACK, WHITE, NONE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public Color change() {
        if (this.isBlack()) {
            return WHITE;
        }

        return BLACK;
    }
}
