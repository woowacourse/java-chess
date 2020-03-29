package domain.pieces;

import domain.move.Direction;
import domain.point.MovePoint;
import domain.point.Point;
import domain.team.Team;
import java.util.Map;

public abstract class Piece {

    protected final String initial;
    protected final Team team;

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

    public Team getTeam() {
        return team;
    }

    public abstract boolean isMovable(Direction direction, Map<Point, Piece> pieces,
        MovePoint movePoint);

    public abstract boolean isNoneTeam();

    @Override
    public String toString() {
        return getInitial();
    }
}
