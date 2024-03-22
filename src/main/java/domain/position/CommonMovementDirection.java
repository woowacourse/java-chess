package domain.position;

import java.util.Arrays;
import java.util.Set;
import java.util.function.BiPredicate;

public enum CommonMovementDirection {
    UP(-1, 0, (rowDistance, columnDistance) -> rowDistance < 0 && columnDistance == 0),
    DOWN(1, 0, (rowDistance, columnDistance) -> rowDistance > 0 && columnDistance == 0),
    RIGHT(0, 1, (rowDistance, columnDistance) -> rowDistance == 0 && columnDistance > 0),
    LEFT(0, -1, (rowDistance, columnDistance) -> rowDistance == 0 && columnDistance < 0),
    UP_RIGHT(-1, 1, (rowDistance, columnDistance) -> rowDistance < 0 && columnDistance > 0),
    UP_LEFT(-1, -1, (rowDistance, columnDistance) -> rowDistance < 0 && columnDistance < 0),
    DOWN_RIGHT(1, 1, (rowDistance, columnDistance) -> rowDistance > 0 && columnDistance > 0),
    DOWN_LEFT(1, -1, (rowDistance, columnDistance) -> rowDistance > 0 && columnDistance < 0);

    private final int rowDistance;
    private final int columnDistance;
    private final BiPredicate<Integer, Integer> condition;

    CommonMovementDirection(final int rowDistance, final int columnDistance, final BiPredicate<Integer, Integer> condition) {
        this.rowDistance = rowDistance;
        this.columnDistance = columnDistance;
        this.condition = condition;
    }

    public static CommonMovementDirection find(final Position source, final Position destination) {
        final int rowDifference = destination.rowIndex() - source.rowIndex();
        final int columnDifference = destination.columnIndex() - source.columnIndex();

        validateDistance(rowDifference, columnDifference);

        return Arrays.stream(CommonMovementDirection.values())
                .filter(unitVector -> unitVector.condition.test(rowDifference, columnDifference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("방향 계산이 불가능한 거리값입니다."));
    }

    private static void validateDistance(final int rowDistance, final int columnDistance) {
        if (rowDistance == 0 && columnDistance == 0
                || (!(Math.abs(rowDistance) == Math.abs(columnDistance))
                && !(rowDistance == 0 || columnDistance == 0))) {
            throw new IllegalArgumentException(("방향 계산이 불가능한 거리값입니다."));
        }
    }

    public static Set<CommonMovementDirection> orthogonalVectors() {
        return Set.of(UP, RIGHT, DOWN, LEFT);
    }

    public static Set<CommonMovementDirection> diagonalVectors() {
        return Set.of(UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT);
    }

    public static Set<CommonMovementDirection> omnidirectionalVectors() {
        return Set.of(UP, RIGHT, DOWN, LEFT, UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT);
    }

    public int getRowDistance() {
        return rowDistance;
    }

    public int getColumnDistance() {
        return columnDistance;
    }
}
