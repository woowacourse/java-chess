package chess.domain;

public enum Color {
    WHITE,
    BLACK,
    NONE,
    ;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isSame(Color color) {
        return this == color;
    }
}
