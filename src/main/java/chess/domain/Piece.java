package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected final Team team;
    protected final Type type;

    public Piece(Team team, Type type) {
        this.team = team;
        this.type = type;
    }

    protected List<Point> move(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        Navigator navigator = new Navigator(start, end);
        Direction direction = navigator.getDirection(type.getDirections());
        Point point = start;
        if (direction.equals(Direction.NOT_FIND)) return points;
        while (!point.equals(end)) {
            point = point.move(direction);
            points.add(point);
        }
        return points;
    }

    protected List<Point> attack(Point start, Point end) {
        return move(start, end);
    }

    boolean isSameTeam(Piece endPiece) {
        return this.team == endPiece.team;
    }

    boolean isSameTeam(Team team) {
        return this.team == team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return team == piece.team &&
                type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, type);
    }
}
