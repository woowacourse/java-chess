package chess.domain.piece;

import chess.domain.Point;
import java.util.List;
import java.util.Optional;

public final class Knight extends Piece {

    private static final String name = "N";

    public Knight(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        List<Point> movablePoints = findMovablePoints(currentPoint);
        return movablePoints.contains(nextPoint);
    }

    private List<Point> findMovablePoints(Point currentPoint) {
        List<Direction> directions = Direction.findKnightDirections();

        return directions.stream()
                .map(direction -> currentPoint.add(direction.getDirectionOfFile(), direction.getDirectionOfRank()))
                .filter(Optional::isPresent).map(Optional::get).toList();
    }
}
