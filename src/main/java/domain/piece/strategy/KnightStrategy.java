package domain.piece.strategy;

import java.util.Arrays;
import java.util.List;

public enum KnightStrategy {

    UP_UP_RIGHT_DIAGONAL(2, 1, List.of(2, 1)),
    UP_UP_LEFT_DIAGONAL(2, -1, List.of(2, -1)),
    LEFT_LEFT_UP_DIAGONAL(1, -2, List.of(1, -2)),
    LEFT_LEFT_DOWN_DIAGONAL(1, -2, List.of(1, -2)),
    RIGHT_RIGHT_UP_DIAGONAL(-1, -2, List.of(-1, -2)),
    RIGHT_RIGHT_DOWN_DIAGONAL(-1, -2, List.of(-1, -2)),
    DOWN_DOWN_RIGHT_DIAGONAL(-2, 1, List.of(-2, 1)),
    DOWN_DOWN_LEFT_DIAGONAL(-2, -1, List.of(-2, -1));

    private final int rowDifference;
    private final int columnDifference;
    private final List<Integer> direction;

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
