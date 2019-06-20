package chess.domain;

import chess.exception.CoordinateOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Coordinate {
    private static final int MIN_BOUND_OF_AXIS = 0;
    private static final int MAX_BOUND_OF_AXIS = 7;
    private static final List<Coordinate> coordinates;

    private final int axis;

    static {
        coordinates = new ArrayList<>();
        IntStream.rangeClosed(MIN_BOUND_OF_AXIS, MAX_BOUND_OF_AXIS)
                .forEach(number -> coordinates.add(new Coordinate(number)));
    }

    private Coordinate(final int axis) {
        this.axis = axis;
    }

    public static Coordinate of(int axis) {
        if (axis < MIN_BOUND_OF_AXIS || axis > MAX_BOUND_OF_AXIS) {
            throw new CoordinateOutOfBoundsException();
        }
        return coordinates.get(axis);
    }
}
