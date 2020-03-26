package domain.pieces;

import domain.point.Point;
import domain.team.Team;
import java.util.Map;

public abstract class Piece {
    private final String initial;
    private final Team team;

    protected Piece(String initial, Team team) {
        this.initial = initial;
        this.team = team;
    }

    public String getInitial() {
        return team.caseInitial(initial);
    }

    public boolean isSameTeam(Team team) {
        return this.team.equals(team);
    }

    public abstract boolean isMovable(Map<Point, Piece> pieces, Point from, Point to);

    @Override
    public String toString() {
        return getInitial();
    }
}
