package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static chess.domain.Direction.*;

public class Bishop extends Piece {
    private final List<Direction> candidateDirection;

    public Bishop(Team team) {
        super(team);
        candidateDirection = Arrays.asList(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }

    @Override
    public List<Point> getCandidatePoints(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        Navigator navigator = new Navigator(start, end);
        Direction direction = navigator.getDirection(candidateDirection);
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
        Bishop bishop = (Bishop) o;
        return team == bishop.team &&
                Objects.equals(candidateDirection, bishop.candidateDirection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, candidateDirection);
    }
}
