package chess.domain;

public enum Camp {
    WHITE,
    BLACK;

    public Camp switchCamp() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
