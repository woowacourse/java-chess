package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class Coordinate {

    public static final int MINIMUM_COORDINATE = 1;
    public static final int MAXIMUM_COORDINATE = 8;
    public static final Map<Integer, Coordinate> matcher;

    static {
        matcher = new HashMap<>();
        for (int i = MINIMUM_COORDINATE; i <= MAXIMUM_COORDINATE; i++) {
            matcher.put(i, new Coordinate(i));
        }
    }

    private final int coordinate;

    private Coordinate(final int coordinate) {
        this.coordinate = coordinate;
    }

    public static Coordinate of(final int coordinate) {
        validate(coordinate);
        return matcher.get(coordinate);
    }

    private static void validate(final int coordinate) {
        if (coordinate < MINIMUM_COORDINATE || coordinate > MAXIMUM_COORDINATE) {
            throw new IllegalArgumentException("좌표는 1부터 8 사이의 값만 가능합니다.");
        }
    }
}
