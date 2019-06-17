package chess.domain;


public class Unit {
    private Position position;
    private Team team;

    public Unit(Position position, Team team) {
        this.position = position;
        this.team = team;
    }
}
