package domain.position;

import java.util.Arrays;
import java.util.Set;
import java.util.function.BiPredicate;

public enum UnitVector {
    INVALID(0, 0, (row, col) -> row == 0 && col == 0),
    UP(-1, 0, (row, col) -> row < 0 && col == 0),
    UP_RIGHT(-1, 1, (row, col) -> row < 0 && col > 0),
    RIGHT(0, 1, (row, col) -> row == 0 && col > 0),
    DOWN_RIGHT(1, 1, (row, col) -> row > 0 && col > 0),
    DOWN(1, 0, (row, col) -> row > 0 && col == 0),
    DOWN_LEFT(1, -1, (row, col) -> row > 0 && col < 0),
    LEFT(0, -1, (row, col) -> row == 0 && col < 0),
    UP_LEFT(-1, -1, (row, col) -> row < 0 && col < 0);

    private final int row;
    private final int col;
    private final BiPredicate<Integer, Integer> condition;

    UnitVector(final int row, final int col, BiPredicate<Integer, Integer> condition) {
        this.row = row;
        this.col = col;
        this.condition = condition;
    }

    public static UnitVector of(final int rowDiff, final int colDiff) {
        if (isInvalid(rowDiff, colDiff)) {
            return INVALID;
        }
        return Arrays.stream(UnitVector.values())
                .filter(unitVector -> unitVector.condition.test(rowDiff, colDiff))
                .findAny()
                .orElse(INVALID);
    }

    private static boolean isInvalid(int rowDiff, int colDiff) {
        return (rowDiff != 0 && colDiff != 0) && (Math.abs(rowDiff) != Math.abs(colDiff));
    }

    public static Set<UnitVector> orthogonalVectors() {
        return Set.of(UP, RIGHT, DOWN, LEFT);
    }

    public static Set<UnitVector> diagonalVectors() {
        return Set.of(UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT);
    }

    public static Set<UnitVector> omnidirectionalVectors() {
        return Set.of(UP, RIGHT, DOWN, LEFT, UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
