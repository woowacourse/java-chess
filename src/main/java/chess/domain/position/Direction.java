package chess.domain.position;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {

    CROSS((rowGap, columnGap) -> rowGap == 0 || columnGap == 0),
    DIAGONAL((rowGap, columnGap) -> Math.abs(rowGap) == Math.abs(columnGap)),
    OTHER((rowGap, columnGap) -> false);

    private final BiPredicate<Integer, Integer> isProperDirection;

    Direction(BiPredicate<Integer, Integer> isProperDirection) {
        this.isProperDirection = isProperDirection;
    }

    public static Direction of(Position start, Position end) {
        int rowGap = start.calculateRowGap(end);
        int columnGap = start.calculateColumnGap(end);

        return Arrays.stream(values())
                .filter(direction -> direction.isProperDirection.test(rowGap, columnGap))
                .findFirst()
                .orElse(Direction.OTHER);
    }
}
