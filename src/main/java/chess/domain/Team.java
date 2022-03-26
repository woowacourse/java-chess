package chess.domain;

public enum Team {
    BLACK,
    WHITE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isSame(Team other) {
        return this.equals(other);
    }

    public Team reverse() {
        if (isWhite()) {
            return BLACK;
        }
        return WHITE;
    }
}
