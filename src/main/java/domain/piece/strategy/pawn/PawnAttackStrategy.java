package domain.piece.strategy.pawn;

import domain.direction.Direction;
import domain.piece.Color;
import java.util.Arrays;
import java.util.List;

public enum PawnAttackStrategy {

    UP_RIGHT_DIAGONAL(1, 1, List.of(1, 1), Color.WHITE),
    UP_LEFT_DIAGONAL(1, -1, List.of(1, -1), Color.WHITE),
    DOWN_LEFT_DIAGONAL(-1, -1, List.of(-1, -1), Color.BLACK),
    DOWN_RIGHT_DIAGONAL(-1, 1, List.of(-1, 1), Color.BLACK);

    private final int rowDifference;
    private final int columnDifference;
    private final List<Integer> direction;
    private final Color allowedPawnColor;

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

    public void validateMoveBackward(Color color) {
        if (allowedPawnColor != color) {
            throw new IllegalArgumentException("폰은 뒤로 갈 수 없습니다.");
        }
    }

    public Direction getDirection() {
        return Direction.from(direction);
    }
}
