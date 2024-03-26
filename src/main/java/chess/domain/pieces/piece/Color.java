package chess.domain.pieces.piece;

public enum Color {
    WHITE,
    BLACK,
    ;

    public boolean isSame(final String color) {
        return this.name().equals(color);
    }

    public Color reverse() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
