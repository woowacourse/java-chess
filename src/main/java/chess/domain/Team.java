package chess.domain;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public boolean isSameTeam(Team team) {
        return this == team;
    }

    public String getTeamColor() {
        return String.valueOf(this);
    }
}
