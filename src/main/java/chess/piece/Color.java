package chess.piece;

public enum Color {

    BLACK,
    WHITE;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isDifferentColor(Color color) {
        return this != color;
    }
}
