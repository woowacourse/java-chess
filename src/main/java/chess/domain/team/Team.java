package chess.domain.team;

public enum Team {
    WHITE,
    BLACK;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public Team reverse() {
        if (isBlack()) {
            return WHITE;
        }
        return BLACK;
    }
}
