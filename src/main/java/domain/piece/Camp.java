package domain.piece;

public enum Camp {
    BLACK,
    WHITE,
    NONE;

    public Camp fetchOppositeCamp() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return NONE;
    }
}
