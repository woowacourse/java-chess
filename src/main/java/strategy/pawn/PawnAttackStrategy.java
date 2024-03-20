package strategy.pawn;

import java.util.Arrays;
import java.util.List;
import piece.Color;

public enum PawnAttackStrategy {

    UP_RIGHT_DIAGONAL(-1, 1, List.of(-1, 1), Color.WHITE),
    UP_LEFT_DIAGONAL(-1, -1, List.of(-1, -1), Color.WHITE),
    DOWN_LEFT_DIAGONAL(1, -1, List.of(1, -1), Color.BLACK),
    DOWN_RIGHT_DIAGONAL(1, 1, List.of(1, 1), Color.BLACK);

    final int rowDifference;
    final int columnDifference;
    final List<Integer> direction;
    final Color allowedPawnColor;

    PawnAttackStrategy(int rowDifference, int columnDifference, List<Integer> direction, Color allowedPawnColor) {
        this.rowDifference = rowDifference;
        this.columnDifference = columnDifference;
        this.direction = direction;
        this.allowedPawnColor = allowedPawnColor;
    }

    public static PawnAttackStrategy getMoveStrategy(int rowDifference, int columnDifference) {

        return Arrays.stream(PawnAttackStrategy.values())
                .filter(enumValue -> enumValue.rowDifference == rowDifference
                        && enumValue.columnDifference == columnDifference)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 움직임 전략이 없습니다."));
    }

    public void validatePossibleStrategyColor(Color color) {
        if (allowedPawnColor != color) {
            throw new IllegalArgumentException("폰은 뒤로 갈 수 없습니다.");
        }
    }

    public List<Integer> getDirection() {
        return direction;
    }
}
