package chess.domain;


public class Unit {
    private Team team;

    public Unit(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }
}
