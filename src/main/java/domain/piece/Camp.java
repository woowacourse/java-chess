package domain.piece;

public enum Camp {
    BLACK,
    WHITE,
    NONE;

    public Camp fetchOppositeCamp() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }
}
