package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class Position {

    private static final Map<Integer, Position> CACHE;

    static {
        final Map<Integer, Position> positions = new HashMap<>();
        for (int x = Board.LOWER_BOUNDARY; x <= Board.UPPER_BOUNDARY; x++) {
            for (int y = Board.LOWER_BOUNDARY; y <= Board.UPPER_BOUNDARY; y++) {
                positions.put(getKey(x, y), new Position(x, y));
            }
        }
        CACHE = positions;
    }

    private static int getKey(final int x, final int y) {
        return (x - Board.LOWER_BOUNDARY) * Board.UPPER_BOUNDARY + y;
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
            throw new IllegalArgumentException("좌표의 값은 " + Board.LOWER_BOUNDARY +  " ~ " +  Board.UPPER_BOUNDARY + " 사이여야 합니다.");
        }
    }

    private static boolean isRangeValid(final int value) {
        return value >= Board.LOWER_BOUNDARY && value <= Board.UPPER_BOUNDARY;
    }
}
