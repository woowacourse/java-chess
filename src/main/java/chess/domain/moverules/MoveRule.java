package chess.domain.moverules;

import java.util.function.BiPredicate;

public enum MoveRule {
    LEFT((rowDiff, columnDiff) -> isNegative(rowDiff) && isZero(columnDiff)),
    RIGHT((rowDiff, columnDiff) -> isPositive(rowDiff) && isZero(columnDiff)),
    TOP((rowDiff, columnDiff) -> isZero(rowDiff) && isPositive(columnDiff)),
    DOWN((rowDiff, columnDiff) -> isZero(rowDiff) && isNegative(columnDiff)),
    DIAGONAL_TOP_LEFT((rowDiff, columnDiff) -> isNegative(rowDiff) && isNegative(columnDiff)),
    DIAGONAL_TOP_RIGHT((rowDiff, columnDiff) -> isPositive(rowDiff) && isNegative(columnDiff)),
    DIAGONAL_DOWN_LEFT((rowDiff, columnDiff) -> isNegative(rowDiff) && isPositive(columnDiff)),
    DIAGONAL_DOWN_RIGHT((rowDiff, columnDiff) -> isNegative(rowDiff) && isPositive(columnDiff));

    private final BiPredicate<Integer, Integer> judge;

    MoveRule(BiPredicate<Integer, Integer> judge) {
        this.judge = judge;
    }

    private static boolean isPositive(int number) {
        return number > 0;
    }

    private static boolean isZero(int number) {
        return number == 0;
    }

    private static boolean isNegative(int number) {
        return number < 0;
    }

    public BiPredicate<Integer, Integer> getJudge() {
        return judge;
    }
}
