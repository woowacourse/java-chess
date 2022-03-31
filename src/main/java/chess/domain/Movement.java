package chess.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Movement {

    EAST_1STEP((columnDiff, rowDiff) -> columnDiff == 1 && rowDiff == 0),
    EAST_UNLIMITED((columnDiff, rowDiff) -> columnDiff >= 1 && rowDiff == 0),

    WEST_1STEP((columnDiff, rowDiff) -> columnDiff == -1 && rowDiff == 0),
    WEST_UNLIMITED((columnDiff, rowDiff) -> columnDiff <= -1 && rowDiff == 0),

    SOUTH_1STEP((columnDiff, rowDiff) -> columnDiff == 0 && rowDiff == 1),
    SOUTH_2STEP((columnDiff, rowDiff) -> columnDiff == 0 && rowDiff == 2),
    SOUTH_UNLIMITED((columnDiff, rowDiff) -> columnDiff == 0 && rowDiff >= 1),

    NORTH_1STEP((columnDiff, rowDiff) -> columnDiff == 0 && rowDiff == -1),
    NORTH_2STEP((columnDiff, rowDiff) -> columnDiff == 0 && rowDiff == -2),
    NORTH_UNLIMITED((columnDiff, rowDiff) -> columnDiff == 0 && rowDiff <= -1),

    SOUTH_EAST_1STEP((columnDiff, rowDiff) -> isDiagonal(columnDiff, rowDiff) && columnDiff == 1 && rowDiff == 1),
    SOUTH_EAST_UNLIMITED((columnDiff, rowDiff) -> isDiagonal(columnDiff, rowDiff) && columnDiff >= 1 && rowDiff >= 1),

    SOUTH_WEST_1STEP((columnDiff, rowDiff) -> isDiagonal(columnDiff, rowDiff) && columnDiff == -1 && rowDiff == 1),
    SOUTH_WEST_UNLIMITED((columnDiff, rowDiff) -> isDiagonal(columnDiff, rowDiff) && columnDiff <= -1 && rowDiff >= 1),

    NORTH_EAST_1STEP((columnDiff, rowDiff) -> isDiagonal(columnDiff, rowDiff) && columnDiff == 1 && rowDiff == -1),
    NORTH_EAST_UNLIMITED((columnDiff, rowDiff) -> isDiagonal(columnDiff, rowDiff) && columnDiff >= 1 && rowDiff <= -1),

    NORTH_WEST_1STEP((columnDiff, rowDiff) -> isDiagonal(columnDiff, rowDiff) && columnDiff == -1 && rowDiff == -1),
    NORTH_WEST_UNLIMITED((columnDiff, rowDiff) -> isDiagonal(columnDiff, rowDiff) && columnDiff <= -1 && rowDiff <= -1),

    KNIGHT_EAST_SOUTH((columnDiff, rowDiff) -> columnDiff == 2 && rowDiff == 1),
    KNIGHT_EAST_NORTH((columnDiff, rowDiff) -> columnDiff == 2 && rowDiff == -1),
    KNIGHT_WEST_SOUTH((columnDiff, rowDiff) -> columnDiff == -2 && rowDiff == 1),
    KNIGHT_WEST_NORTH((columnDiff, rowDiff) -> columnDiff == -2 && rowDiff == -1),
    KNIGHT_SOUTH_EAST((columnDiff, rowDiff) -> columnDiff == 1 && rowDiff == 2),
    KNIGHT_SOUTH_WEST((columnDiff, rowDiff) -> columnDiff == -1 && rowDiff == 2),
    KNIGHT_NORTH_EAST((columnDiff, rowDiff) -> columnDiff == 1 && rowDiff == -2),
    KNIGHT_NORTH_WEST((columnDiff, rowDiff) -> columnDiff == -1 && rowDiff == -2);

    private final BiPredicate<Integer, Integer> findPredicate;

    Movement(BiPredicate<Integer, Integer> findPredicate) {
        this.findPredicate = findPredicate;
    }

    public static Movement find(int columnDifference, int rowDifference) {
        return Arrays.stream(values())
                .filter(movement -> movement.findPredicate.test(columnDifference, rowDifference))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게임에서 허용되지 않은 이동입니다."));
    }

    private static boolean isDiagonal(int columnDiff, int rowDiff) {
        return Math.abs(columnDiff) == Math.abs(rowDiff);
    }

    public boolean is2Step() {
        return this == SOUTH_2STEP || this == NORTH_2STEP;
    }

    public boolean isDiagonal() {
        return this == SOUTH_EAST_1STEP ||
                this == SOUTH_EAST_UNLIMITED ||
                this == SOUTH_WEST_1STEP ||
                this == SOUTH_WEST_UNLIMITED ||
                this == NORTH_EAST_1STEP ||
                this == NORTH_EAST_UNLIMITED ||
                this == NORTH_WEST_1STEP ||
                this == NORTH_WEST_UNLIMITED;
    }
}
