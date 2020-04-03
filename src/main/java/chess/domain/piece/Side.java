package chess.domain.piece;

public enum Side {
    BLACK("흑"),
    WHITE("백"),
    NONE("무");

    private final String value;

    Side(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public Side opposite() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return NONE;
    }
}
