package domain.piece.strategy;

import java.util.Arrays;
import java.util.List;

public enum BishopStrategy {

    UP_LEFT_DIAGONAL(-1, 1, List.of(-1, 1)),
    UP_RIGHT_DIAGONAL(1, 1, List.of(1, 1)),
    DOWN_LEFT_DIAGONAL(-1, -1, List.of(-1, -1)),
    DOWN_RIGHT_DIAGONAL(1, -1, List.of(1, -1));

    private final int rowDifference;
    private final int columnDifference;
    private final List<Integer> direction;

    BishopStrategy(int rowDifference, int columnDifference, List<Integer> direction) {
        this.rowDifference = rowDifference;
        this.columnDifference = columnDifference;
        this.direction = direction;
    }

    public static BishopStrategy getMoveStrategy(int rowDifference, int columnDifference) {
        return Arrays.stream(BishopStrategy.values())
                .filter(strategy -> strategy.rowDifference == divideValueByAbs(rowDifference)
                        && strategy.columnDifference == divideValueByAbs(columnDifference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 움직임 전략이 없습니다."));
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
