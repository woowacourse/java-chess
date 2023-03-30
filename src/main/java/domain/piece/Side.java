package domain.piece;

public enum Side {
    BLACK,
    WHITE,
    NEUTRAL;

    public Side nextSide() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
