package chess.domain;
public enum Team {

    BLACK,
    WHITE;

    public boolean isSameTeam(Team team) {
        return this == team;
    }
}
