package chess.domain.piece;

import chess.domain.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Pawn extends Piece {

    private static final String NAME = "P";
    private static final int TWO_RANK = 2;

    public Pawn(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        Team team = getTeam();
        List<Point> movablePoints = findMovablePoints(currentPoint, team);

        return movablePoints.contains(nextPoint);
    }

    private List<Point> findMovablePoints(Point currentPoint, Team team) {
        List<Point> points = new ArrayList<>();
        int forwardDirection = team.forwardDirection();

        if (currentPoint.isFirstPoint()) {
            Optional<Point> pointOpt = currentPoint.add(0, TWO_RANK * forwardDirection);
            pointOpt.ifPresent(points::add);
        }
        for (Direction direction : Direction.findPawnDirections()) {
            Optional<Point> pointOpt = currentPoint.add(direction.file(), direction.rank() * forwardDirection);
            pointOpt.ifPresent(points::add);
        }
        return points;
    }
}
