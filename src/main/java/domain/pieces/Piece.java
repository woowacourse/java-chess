package domain.pieces;

import domain.move.Direction;
import domain.point.MovePoint;
import domain.point.Point;
import domain.team.Team;
import java.util.List;
import java.util.Map;

public abstract class Piece {

    private static final String INITIAL_KING = "k";

    protected final String initial;
    protected final Team team;

    protected Piece(String initial, Team team) {
        this.initial = initial(team, initial);
        this.team = team;
    }

    private String initial(Team team, String initial) {
        if (team.equals(Team.BLACK)) {
            return initial.toUpperCase();
        }
        return initial.toLowerCase();
    }

    public String getInitial() {
        return initial;
    }

    public boolean isSameTeam(Team team) {
        return this.team.equals(team);
    }

    public Team getTeam() {
        return team;
    }

    public boolean isKing() {
        return initial.equalsIgnoreCase(INITIAL_KING);
    }

    public abstract boolean isMovable(Direction direction, Map<Point, Piece> pieces,
        MovePoint movePoint);

    public abstract boolean isNoneTeam();

    public abstract double getScore(Map<Point, Piece> pieces, Point point);

    public abstract List<Direction> getDirection(Team team);

    @Override
    public String toString() {
        return getInitial();
    }
}
