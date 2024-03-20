package strategy.pawn;

import java.util.Arrays;
import java.util.List;

public enum PawnAttackStrategy {

    UP(-1, 0, List.of(-1, 0), false),
    DOWN(1, 0, List.of(1, 0), true),
    UP_UP(-2, 0, List.of(-1, 0), false),
    DOWN_DOWN(2, 0, List.of(1, 0), true),
    UP_RIGHT_DIAGONAL(-1, 1, List.of(-1, 1), false),
    UP_LEFT_DIAGONAL(-1, -1, List.of(-1, -1), false),
    DOWN_LEFT_DIAGONAL(1, -1, List.of(1, -1), true),
    DOWN_RIGHT_DIAGONAL(1, 1, List.of(1, 1), true);

    final int rowDifference;
    final int columnDifference;
    final List<Integer> direction;
    final boolean isBlack;

    PawnAttackStrategy(int rowDifference, int columnDifference, List<Integer> direction, boolean isBlack) {
        this.rowDifference = rowDifference;
        this.columnDifference = columnDifference;
        this.direction = direction;
        this.isBlack = isBlack;
    }

    public static PawnAttackStrategy getMoveStrategy(int xDifference, int yDifference) {

        return Arrays.stream(PawnAttackStrategy.values())
                .filter(enumValue -> enumValue.rowDifference == xDifference
                        && enumValue.columnDifference == yDifference)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 움직임 전략이 없습니다."));
    }

    public void validatePossibleStrategyColor(boolean isBlackPawn) {
        if (isBlack != isBlackPawn) {
            throw new IllegalArgumentException("폰은 뒤로 갈 수 없습니다.");
        }
    }

    public List<Integer> getDirection() {
        return direction;
    }
}
