package chess.domain.pieces.piece;

public enum Color {
    WHITE,
    BLACK,
    ;

    public boolean isSame(final String color) {
        return this.name().equals(color);
    }

    public Color exchangeTurn() {
        if (this.equals(Color.WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
