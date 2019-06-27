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
    NOT_FIND(0, 0),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private static final int FIRST_VALUE = 0;
    private Point position;

    Direction(int positionX, int positionY) {
        this.position = new Point(positionX, positionY);
    }

    public static Direction findDirection(Point point) {
        List<Direction> directions = Arrays.stream(values())
                .filter(direction -> direction.position.equals(point))
                .collect(Collectors.toList());

        return (directions.isEmpty()) ? NOT_FIND : directions.get(FIRST_VALUE);
    }

    public Point getPosition() {
        return position;
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<Direction> blankDirection() {
        return Arrays.asList(NOT_FIND);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST);
    }
}
