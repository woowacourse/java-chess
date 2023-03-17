package chess.domain;

public enum Team {

    BLACK,
    WHITE;

    public boolean isSameTeam(Team team) {
        return this == team;
    }

    public Team reverse() {
        if (WHITE == this) {
            return BLACK;
        }
        return WHITE;
    }

    public static Team getStartTeam() {
        return WHITE;
    }
}
