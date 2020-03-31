package domain.team;

public enum Team {
    BLACK,
    WHITE,
    NONE;

    public static Team opposite(Team team) {
        if (BLACK.equals(team)) {
            return WHITE;
        }
        return BLACK;
    }
}
