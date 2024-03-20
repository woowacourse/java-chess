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

    final int x;
    final int y;
    final List<Integer> strategy;

    KnightStrategy(int x, int y, List<Integer> strategy) {
        this.x = x;
        this.y = y;
        this.strategy = strategy;
    }

    public static List<Integer> getMoveStrategy(int rowDifference, int columnDifference) {
        KnightStrategy knightStrategy = Arrays.stream(KnightStrategy.values())
                .filter(enumValue -> enumValue.x == rowDifference && enumValue.y == columnDifference)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 움직임 전략이 없습니다."));

        return knightStrategy.strategy;
    }
}
