package chess.domain;
public enum Team {

    BLACK,
    WHITE;

    boolean isSameTeam(Team team) {
        return this == team;
    }
}
