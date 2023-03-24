package techcourse.fp.chess.domain.piece;

public enum Color {
    BLACK,
    WHITE,
    EMPTY;

    public boolean isSameColor(final Color color) {
        return this == color;
    }

    public boolean isOpponent(final Color color) {
        return this == BLACK && color == WHITE || this == WHITE && color == BLACK;
    }

    public Color getOtherSide() {
        if (this == BLACK) {
            return WHITE;
        }

        if (this == WHITE) {
            return BLACK;
        }

        throw new IllegalStateException("백이나 흑이 아닙니다.");
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
