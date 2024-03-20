package strategy;

import java.util.Arrays;
import java.util.List;

public enum KnightStrategy {

    UUR(-2, 1, List.of(-2, 1)),
    UUL(-2, -1, List.of(-2, -1)),
    LLU(-1, -2, List.of(-1, -2)),
    LLD(1, -2, List.of(1, -2)),
    RRU(-1, 2, List.of(-1, 2)),
    RRD(1, 2, List.of(1, 2)),
    DDR(2, -1, List.of(2, -1)),
    DDL(2, 1, List.of(2, 1));

    final int rowDifference;
    final int columnDifference;
    final List<Integer> direction;

    KnightStrategy(int rowDifference, int columnDifference, List<Integer> direction) {
        this.rowDifference = rowDifference;
        this.columnDifference = columnDifference;
        this.direction = direction;
    }

    public static KnightStrategy getMoveStrategy(int rowDifference, int columnDifference) {
        return Arrays.stream(KnightStrategy.values())
                .filter(enumValue -> enumValue.rowDifference == rowDifference
                        && enumValue.columnDifference == columnDifference)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 움직임 전략이 없습니다."));
    }

    public List<Integer> getDirection() {
        return this.direction;
    }
}
