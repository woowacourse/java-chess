package chess.domain.piece;

public enum Team {
    BLACK,
    WHITE,
    NONE;

    Team() {
    }

    public boolean isSame(Team team) {
        return this == team;
    }

    public boolean isNotNone() {
        return this != NONE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
