package chess.domain.piece;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    public boolean isSameColor(final Team team) {
        return this == team;
    }

    public boolean isOpponent(final Team team) {
        return this == BLACK && team == WHITE || this == WHITE && team == BLACK;
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
