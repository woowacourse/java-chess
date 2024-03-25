package domain.piece.strategy;

import domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public enum QueenStrategy {
    UP(1, 0, List.of(1, 0)),
    DOWN(-1, 0, List.of(-1, 0)),
    LEFT(0, 1, List.of(0, 1)),
    RIGHT(0, -1, List.of(0, -1)),
    UP_LEFT_DIAGONAL(-1, 1, List.of(-1, 1)),
    UP_RIGHT_DIAGONAL(1, 1, List.of(1, 1)),
    DOWN_LEFT_DIAGONAL(-1, -1, List.of(-1, -1)),
    DOWN_RIGHT_DIAGONAL(1, -1, List.of(1, -1));

    private final int rowDifference;
    private final int columnDifference;
    private final List<Integer> direction;

    QueenStrategy(int rowDifference, int columnDifference, List<Integer> direction) {
        this.rowDifference = rowDifference;
        this.columnDifference = columnDifference;
        this.direction = direction;
    }

    public static QueenStrategy getMoveStrategy(int rowDifference, int columnDifference) {
        return Arrays.stream(QueenStrategy.values())
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

    public Direction getDirection() {
        return Direction.from(direction);
    }
}
