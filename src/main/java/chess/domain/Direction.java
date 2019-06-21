package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Direction {
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),
    NORTH(0, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    NOT_FIND(0, 0);

    private Point position;

    Direction(int positionX, int positionY) {
        this.position = new Point(positionX, positionY);
    }

    public static Direction findDirection(Point point) {
        List<Direction> directions = Arrays.stream(values())
                .filter(d -> d.position.equals(point)).collect(Collectors.toList());
        if (directions.size() != 0) {
            return directions.get(0);
        }
        return NOT_FIND;
    }

    public Point getPosition() {
        return position;
    }
}
