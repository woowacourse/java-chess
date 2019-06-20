package chess.domain;

import java.util.List;

public class Navigator {
    private final Direction direction;

    public Navigator(Point start, Point end) {
        Point directionPoint = start.calDirection(end);
        this.direction = Direction.findDirection(directionPoint);
    }

    public Direction getDirection(List<Direction> directions) {
        return directions.contains(direction) ? direction : Direction.NOT_FIND;
    }
}
