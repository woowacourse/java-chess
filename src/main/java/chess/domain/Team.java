package chess.domain;

public enum Team {

    BLACK,
    WHITE;

    public boolean isSameTeam(Team team) {
        return this == team;
    }

    public Team reverse() {
        if (Team.WHITE == this) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }
}
