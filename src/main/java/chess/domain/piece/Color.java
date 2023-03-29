package chess.domain.piece;


public enum Color {
    WHITE,
    BLACK;

    public Color next() {
        if (this == Color.BLACK) return Color.WHITE;
        return Color.BLACK;
    }
}
