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
    public boolean canReach(final Position from, final Position to, final List<Position> pieces) {
        return getPossiblePosition(from)
                .stream()
                .filter(position -> !pieces.contains(position))
                .anyMatch(position -> position.equals(to));
    }

    private List<Position> getPossiblePosition(final Position from) {
        return points.stream()
                .filter(point -> point.x + from.rank() > 0 && point.x + from.rank() < 9)
                .filter(point -> point.y + from.file() > 0 && point.y + from.file() < 9)
                .map(point -> new Position(point.y + from.file(), point.x + from.rank()))
                .toList();
    }
}

