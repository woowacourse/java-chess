package chess.team;

public enum Team {
    BLACK,
    WHITE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isReverseTeam(Team team) {
        return this != team;
    }

    public Team changeTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
