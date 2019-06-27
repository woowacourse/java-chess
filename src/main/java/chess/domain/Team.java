package chess.domain;

public enum Team {
    WHITE,
    BLACK,
    BLANK;

    public static Team switchTeam(Team team) {
        if (WHITE.equals(team)) {
            return BLACK;
        }
        return WHITE;
    }
}
