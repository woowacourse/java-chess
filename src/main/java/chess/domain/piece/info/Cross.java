package chess.domain.piece.info;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Cross implements Direction {
    UP(Position::isUp, new int[]{0, 1}),
    DOWN(Position::isDown, new int[]{0, -1}),
    RIGHT(Position::isRight, new int[]{1, 0}),
    LEFT(Position::isLeft, new int[]{-1, 0});

    private final BiPredicate<Position, Position> findCross;
    private final int[] changeValues;

    Cross(BiPredicate<Position, Position> findCross, int[] changeValues) {
        this.findCross = findCross;
        this.changeValues = changeValues;
    }

    public static Cross findCrossByTwoPosition(Position source, Position target) {
        return Arrays.stream(values())
                .filter(value -> source.isCross(target))
                .filter(value -> value.findCross.test(source, target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(DIRECTION_ERROR));
    }

    public int[] getChangeValues() {
        return changeValues;
    }
}
