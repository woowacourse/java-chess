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
}
