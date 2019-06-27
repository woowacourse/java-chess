package chess.domain.board;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Coordinate {
    public static final int MIN_COORDINATE = 0;
    public static final int MAX_COORDINATE = 7;

    public int getCoordinate() {
        return coordinate;
    }

    private static Map<Integer, Coordinate> COORDINATE_POOL;

    static {
        COORDINATE_POOL = IntStream.rangeClosed(MIN_COORDINATE, MAX_COORDINATE)
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
        if (coordinate < MIN_COORDINATE || coordinate > MAX_COORDINATE) {
            throw new IllegalArgumentException();
        }
    }

    public int calculateDistance(Coordinate end) {
        return this.coordinate - end.coordinate;
    }

    public int sumCoordinate(int num) {
        return coordinate + num;
    }
}
