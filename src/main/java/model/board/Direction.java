package model.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum Direction {
    NORTH_WEST(-1, 1),
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0);

    final int offsetX;
    final int offsetY;

    Direction(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public static Stream<Direction> rotateClockwiseFromTo(Direction begin, int number) {
        List<Direction> result = new ArrayList<>(Arrays.asList(values()));
        result.addAll(result);
        return result.subList(result.indexOf(begin), result.indexOf(begin) + number).stream();
    }

    public static Stream<Direction> orthogonal() {
        return Stream.of(NORTH, EAST, SOUTH, WEST);
    }

    public static Stream<Direction> diagonal() {
        return Stream.of(NORTH_WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST);
    }

    public Direction rotateClockwise(int number) {
        return values()[(this.ordinal() + number) % values().length];
    }

    public Direction rotateCounterClockwise(int number) {
        return rotateClockwise(values().length - number % values().length);
    }

    // TODO: 2019-06-25 rotation with large numbers might break
}