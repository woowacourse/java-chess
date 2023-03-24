package chess.domain.piece;

public enum Camp {
    WHITE("White"),
    BLACK("Black"),
    EMPTY("Empty");

    final String name;

    Camp(final String name) {
        this.name = name;
    }

    public static Camp nextTurn(final Camp turn) {
        if (turn.equals(WHITE)) {
            return BLACK;
        }
        if (turn.equals(BLACK)) {
            return WHITE;
        }
        return EMPTY;
    }

    public String getName() {
        return name;
    }
}
