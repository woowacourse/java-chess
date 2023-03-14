package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class Position {

    private static final Map<Integer, Position> CACHE;
    private static final int LOWER_BOUNDARY = 1;
    private static final int UPPER_BOUNDARY = 8;

    static {
        final Map<Integer, Position> positions = new HashMap<>();
        for (int x = LOWER_BOUNDARY; x <= UPPER_BOUNDARY; x++) {
            for (int y = LOWER_BOUNDARY; y <= UPPER_BOUNDARY; y++) {
                positions.put(getKey(x, y), new Position(x, y));
            }
        }
        CACHE = positions;
    }

    private static int getKey(final int x, final int y) {
        return (x - LOWER_BOUNDARY) * UPPER_BOUNDARY + y;
    }

    private final int x;
    private final int y;

    private Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(final int x, final int y) {
        validate(x, y);
        return CACHE.get(getKey(x, y));
    }

    private static void validate(final int x, final int y) {
        if (!isRangeValid(x) || !isRangeValid(y)) {
            throw new IllegalArgumentException("좌표의 값은 " + LOWER_BOUNDARY +  " ~ " +  UPPER_BOUNDARY + " 사이여야 합니다.");
        }
    }

    private static boolean isRangeValid(final int value) {
        return value >= LOWER_BOUNDARY && value <= UPPER_BOUNDARY;
    }
}
