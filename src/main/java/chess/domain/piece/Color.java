package chess.domain.piece;

public enum Color {
    WHITE,
    BLACK,
    NONE;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isNone() {
        return this == NONE;
    }
}
