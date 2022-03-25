package chess.domain.piece;

public enum Team {
    BLACK, WHITE, NONE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isNone() {
        return this == NONE;
    }
}
