package domain.piece;

public enum Camp {
    BLACK,
    WHITE,
    EMPTY;

    public Camp fetchOppositeCamp() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return EMPTY;
    }
}
