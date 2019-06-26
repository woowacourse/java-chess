package chess.domain;

public enum Team {
    WHITE,
    BLACK,
    BLANK;

    public static Team switchTeam(Team team) {
        if (team.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
