package chess.domain.board;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Coordinate {
    private static final int MIN = 1;
    private static final int MAX = 8;

    private static Map<Integer, Coordinate> COORDINATE_POOL;

    static {
        COORDINATE_POOL = IntStream.rangeClosed(MIN, MAX)
                .boxed()
                .collect(toMap(identity(), Coordinate::new));
    }

    private int coordinate;

    private Coordinate(int coordinate) {
        this.coordinate = coordinate;
    }

    public static Coordinate of(int coordinate) {
        validateBoundary(coordinate);
        return COORDINATE_POOL.get(coordinate);
    }

    private static void validateBoundary(int coordinate) {
        if (coordinate < MIN || coordinate > MAX) {
            throw new IllegalArgumentException();
        }
    }
}
