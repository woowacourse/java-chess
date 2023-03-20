package chess.piece;

public enum Color {

    BLACK,
    WHITE,
    EMPTY;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public Color opposite() {
        if (isBlack()) {
            return WHITE;
        }
        if (isWhite()) {
            return BLACK;
        }
        if (isEmpty()) {
            return EMPTY;
        }

        throw new UnsupportedOperationException();
    }
}
