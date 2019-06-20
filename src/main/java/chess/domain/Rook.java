package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rook implements Piece{
    private final boolean teamColor;

    public Rook(boolean teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public List<Point> getCandidatePoints(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        Navigator navigator = new Navigator(start, end);
        Direction direction = navigator.getDirection();
        Point point = start;
        if (direction.equals(Direction.NOT_FIND)) return points;
        while(!point.equals(end)) {
            point = point.move(direction);
            points.add(point);
        }
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rook rook = (Rook) o;
        return teamColor == rook.teamColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor);
    }
}
