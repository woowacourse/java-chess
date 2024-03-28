package chess.domain.piece;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    public boolean isWhite() {
        return this == WHITE;
    }

    public int forwardDirection() {
        return this.isWhite() ? 1 : -1;
    }

    public Team opponent() {
        if (this == EMPTY) {
            return this;
        }
        return this == WHITE ? BLACK : WHITE;
    }
}
