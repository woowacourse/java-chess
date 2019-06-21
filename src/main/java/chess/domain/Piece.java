package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    protected final Team team;
    protected final List<Direction> candidateDirection;

    public Piece(Team team, List<Direction> candidateDirection) {
        this.team = team;
        this.candidateDirection = candidateDirection;
    }

    protected List<Point> getCandidatePoints(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        Navigator navigator = new Navigator(start, end);
        Direction direction = navigator.getDirection(candidateDirection);
        Point point = start;
        if (direction.equals(Direction.NOT_FIND)) return points;
        while (!point.equals(end)) {
            point = point.move(direction);
            points.add(point);
        }
        return points;
    }

    boolean isSameTeam(Piece endPiece) {
        return this.team == endPiece.team;
    }
}
