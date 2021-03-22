package chess.domain;

public enum TeamColor {
    WHITE, BLACK;

    public TeamColor reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isReverse(TeamColor teamColor) {
        return teamColor != this;
    }
}
