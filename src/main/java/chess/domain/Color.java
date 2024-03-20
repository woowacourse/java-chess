package chess.domain;

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
