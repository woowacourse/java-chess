package domain.piece.strategy;

import java.util.Arrays;
import java.util.List;

public enum KingStrategy {

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

    KingStrategy(int rowDifference, int columnDifference, List<Integer> direction) {
        this.rowDifference = rowDifference;
        this.columnDifference = columnDifference;
        this.direction = direction;
    }

    public static KingStrategy getMoveStrategy(int rowDifference, int columnDifference) {
        return Arrays.stream(KingStrategy.values())
                .filter(strategy -> strategy.rowDifference == rowDifference
                        && strategy.columnDifference == columnDifference)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 움직임 전략이 없습니다."));
    }

    public List<Integer> getDirection() {
        return this.direction;
    }
}
