package chess.domain;

public enum Team {
    BLACK,
    WHITE,
    NONE;

    public Team opposite() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return NONE;
    }
}
