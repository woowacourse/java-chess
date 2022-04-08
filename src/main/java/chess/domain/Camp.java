package chess.domain;

public enum Camp {
    WHITE,
    BLACK,
    NONE;

    public Camp switchCamp() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
