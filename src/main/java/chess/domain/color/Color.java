package chess.domain.color;

public enum Color {
    WHITE,
    BLACK,
    NONE;

    public Color findOppositeColor() {
        if (this == Color.WHITE) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }
}
