package chess.model.piece;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Coordinate {
    private static Map<Integer, Coordinate> coordinates = new HashMap<>();
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 8;

    private int coordinate;

    private Coordinate(int currentCoordinate) {
        this.coordinate = currentCoordinate;
    }

    static {
        coordinates = IntStream.rangeClosed(MIN_RANGE, MAX_RANGE)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), Coordinate::new));
    }

    public static Coordinate valueOf(int position) {
        if (position > MAX_RANGE || position < MIN_RANGE) {
            throw new IllegalArgumentException("범위를 벗어났습니다");
        }
        return coordinates.get(position);
    }
}
