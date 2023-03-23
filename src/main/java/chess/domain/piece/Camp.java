package chess.domain.piece;

public enum Camp {
    WHITE, BLACK, EMPTY;

    public static Camp nextTurn(final Camp turn) {
        if (turn.equals(WHITE)) {
            return BLACK;
        }
        if (turn.equals(BLACK)) {
            return WHITE;
        }
        return EMPTY;
    }
}
