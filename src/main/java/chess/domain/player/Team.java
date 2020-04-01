package chess.domain.player;

public enum Team {

    WHITE,
    BLACK;

    public Team toggle() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isSameTeam(Team team) {
        return this == team;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
