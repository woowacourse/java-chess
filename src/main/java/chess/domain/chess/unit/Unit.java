package chess.domain.chess.unit;

import chess.domain.chess.Team;
import chess.domain.geometric.Vector;

abstract public class Unit {
    private Team team;
    private String name;

    public Unit(Team team, String name) {
        this.team = team;
        this.name = name;
    }

    public abstract boolean validateDirection(Vector vector);
    public abstract double score();

    public boolean isEqualTeam(Unit unit) {
        return this.team.equals(unit.team);
    }

    public Team getTeam() {
        return team;
    }

    public String getName() {
        return this.name;
    }
}
