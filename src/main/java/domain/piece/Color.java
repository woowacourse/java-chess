package domain.piece;

public enum Color {
    BLACK,
    WHITE;

    public Color switchTurn() {
        if (this == Color.BLACK) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
