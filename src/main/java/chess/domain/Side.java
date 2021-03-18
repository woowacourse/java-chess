package chess.domain;

public enum Side {
    WHITE, BLACK, NONE;

    public Side changeTurn() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        throw new IllegalArgumentException("NONE");
    }
}
