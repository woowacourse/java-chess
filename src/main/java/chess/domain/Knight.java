package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Knight implements Piece {
    private static final int MOVABLE_DISTANCE = 5;
    private final Team team;

    public Knight(Team team) {
        this.team = team;
    }

    @Override
    public List<Point> getCandidatePoints(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        if (start.calDistance(end) == MOVABLE_DISTANCE) {
            points.add(end);
        }
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knight knight = (Knight) o;
        return team == knight.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
