package chess.domain;

public enum Color {
    BLACK, WHITE, NONE;

    public boolean isBlack() {
        return this == BLACK;
    }
}
