package chess.domain.game;

public enum Side {
    WHITE, BLACK, NONE;

    public Side changeTurn() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }

        throw new UnsupportedOperationException("NONE은 Side 교체 불가.");
    }
}
