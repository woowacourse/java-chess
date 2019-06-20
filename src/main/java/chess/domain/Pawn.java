package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static chess.domain.Direction.*;

public class Pawn implements Piece{
    private final Team team;
    private final List<Direction> candidateDirection;

    public Pawn(Team team) {
        this.team = team;
        this.candidateDirection = (team == Team.WHITE) ? Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST) :
                Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST);
    }

    @Override
    public List<Point> getCandidatePoints(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        Navigator navigator = new Navigator(start, end);
        Direction foundDirection = navigator.getDirection(candidateDirection);
        if (isTwoStep(start, end, foundDirection)) {
            return Arrays.asList(start.move(foundDirection), end);
        }
        if (isOneStep(start, end, foundDirection)) {
            points.add(start.move(foundDirection));
        }
        return points;
    }

    private boolean isOneStep(Point start, Point end, Direction foundDirection) {
        return candidateDirection.contains(foundDirection) &&
                start.isStep(end, 1);
    }

    private boolean isTwoStep(Point start, Point end, Direction foundDirection) {
        return foundDirection.equals(team.getDirection()) &&
                start.isSameY(team.getFirstIndex()) &&
                start.isStep(end, 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pawn pawn = (Pawn) o;
        return team == pawn.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
