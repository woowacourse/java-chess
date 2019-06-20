package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static chess.domain.Direction.*;

public class Pawn implements Piece{
    private final boolean teamColor;
    private final List<Direction> candidateDirection;

    public Pawn(boolean teamColor) {
        this.teamColor = teamColor;
        this.candidateDirection = (teamColor) ? Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST) :
                Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST);
    }

    @Override
    public List<Point> getCandidatePoints(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        Point vector = start.makeVector(end);
        Direction foundDirection = Direction.findDirection(vector);
        if(candidateDirection.contains(foundDirection)){
            points.add(end);
        }
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pawn pawn = (Pawn) o;
        return teamColor == pawn.teamColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor);
    }
}
