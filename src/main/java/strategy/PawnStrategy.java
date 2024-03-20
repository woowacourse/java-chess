package strategy;

import java.util.Arrays;
import java.util.List;

public enum PawnStrategy {

    U(-1, 0, List.of(-1, 0)),
    D(1, 0, List.of(1, 0)),
    UU(-2, 0, List.of(-1, 0)),
    DD(2, 0, List.of(1, 0));

    final int x;
    final int y;
    final List<Integer> strategy;

    PawnStrategy(int x, int y, List<Integer> strategy) {
        this.x = x;
        this.y = y;
        this.strategy = strategy;
    }

    public static List<Integer> getMoveStrategy(int xDifference, int yDifference) {
        PawnStrategy pawnStrategy = Arrays.stream(PawnStrategy.values())
                .filter(enumValue -> enumValue.x == xDifference && enumValue.y == yDifference)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 움직임 전략이 없습니다."));

        return pawnStrategy.strategy;
    }
}
