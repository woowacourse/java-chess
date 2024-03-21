package chess.domain.movement.direction;

import chess.domain.Position;
import java.awt.Point;
import java.util.List;

public class KnightDirection implements Direction {

    private final List<Point> points = List.of(new Point(1, 2),
            new Point(1, -2),
            new Point(-1, 2),
            new Point(-1, -2),
            new Point(2, 1),
            new Point(2, -1),
            new Point(-2, 1),
            new Point(-2, -1));

    @Override
    public boolean canReach(final Position source, final Position target, final List<Position> pieces) {
        return getPossiblePosition(source)
                .stream()
                .filter(position -> !pieces.contains(position))
                .anyMatch(position -> position.equals(target));
    }

    private List<Position> getPossiblePosition(final Position position) {
        return points.stream()
                .filter(position::isNextPositionInRange)
                .map(point -> new Position(point.x + position.file(), point.y + position.rank()))
                .toList();
    }
}

