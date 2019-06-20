package chess.domain;

public class Navigator {
    private final Direction direction;

    public Navigator(Point start, Point end) {
        Point directionPoint = start.calDirection(end);
        this.direction = Direction.findDirection(directionPoint);
    }

    public Direction getDirection() {
        return direction;
    }
}
