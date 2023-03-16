package chess.domain;

public enum Color {
    BLACK,
    WHITE,
    BLANK;

    public boolean isBlack() {
        return this == BLACK;
    }
}
