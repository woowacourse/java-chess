package chess.domain;

public enum Team {
    BLACK,
    WHITE;

    public Team turnOver(Team team) {
        if (team == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
