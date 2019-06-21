package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class King extends Piece {
    public King(Team team) {
        super(team);
    }

    @Override
    public List<Point> getCandidatePoints(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        Point vector = start.makeVector(end);
        Direction foundDirection = Direction.findDirection(vector);
        if(foundDirection != Direction.NOT_FIND) {
            points.add(end);
        }
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        King king = (King) o;
        return team == king.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
