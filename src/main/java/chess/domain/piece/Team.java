package chess.domain.piece;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    public int forwardDirection() {
        if (isWhite()) {
            return 1;
        }
        return -1;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
