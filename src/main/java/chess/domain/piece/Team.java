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

    public Team opposite() {
        if (isWhite()) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
