package strategy;

import java.util.Arrays;
import java.util.List;

public enum RookStrategy {

    UP(-1, 0, List.of(-1, 0)),
    DOWN(1, 0, List.of(1, 0)),
    LEFT(0, -1, List.of(0, -1)),
    RIGHT(0, 1, List.of(0, 1));

    private final int rowDifference;
    private final int columnDifference;
    private final List<Integer> direction;

    RookStrategy(int rowDifference, int columnDifference, List<Integer> direction) {
        this.rowDifference = rowDifference;
        this.columnDifference = columnDifference;
        this.direction = direction;
    }

    public static RookStrategy getMovingStrategy(int rowDifference, int columnDifference) {
        return Arrays.stream(RookStrategy.values())
                .filter(strategy -> strategy.rowDifference == divideValueByAbs(rowDifference)
                        && strategy.columnDifference == divideValueByAbs(columnDifference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("invalid move"));
    }

    private static int divideValueByAbs(int value) {
        if (value == 0) {
            return 0;
        }
        return value / Math.abs(value);
    }

    public List<Integer> getDirection() {
        return this.direction;
    }
}
