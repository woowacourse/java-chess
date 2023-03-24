package domain.piece;

public enum TeamColor {
    BLACK, WHITE, EMPTY;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isSame(TeamColor otherColor) {
        return this == otherColor;
    }

    public boolean isDifferent(TeamColor otherColor) {
        return this != otherColor;
    }
}
