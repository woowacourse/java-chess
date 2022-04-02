package chess.domain;

public enum Team {
    BLACK,
    WHITE,
    NONE;

    public static Team switchTeam(Team team) {
        if (team == BLACK) {
            return WHITE;
        }
        if (team == WHITE) {
            return BLACK;
        }
        return NONE;
    }
}
