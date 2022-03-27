package chess.domain.piece;

public enum Color {

    WHITE, BLACK, NONE;

    public Color toggle() {
        validateColor();
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    private void validateColor() {
        if (this == NONE) {
            throw new IllegalArgumentException("[ERROR] None color can't be toggled");
        }
    }
}
