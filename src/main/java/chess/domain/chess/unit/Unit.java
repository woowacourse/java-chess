package chess.domain.chess.unit;

import chess.domain.chess.Team;
import chess.domain.geometric.Vector;

abstract public class Unit {
    private Team team;
    private Attribute attribute;

    public Unit(Team team, Attribute attribute) {
        this.team = team;
        this.attribute = attribute;
    }

    public abstract boolean validateDirection(Vector vector);

    public boolean isEqualTeam(Unit unit) {
        return this.team.equals(unit.team);
    }

    public Team getTeam() {
        return team;
    }

    public String getName() {
        return attribute.name();
    }

    public String getSymbol() {
        return attribute.getSymbol();
    }

    public double getScore() {
        return attribute.getScore();
    }
}
