package chess.domain.piece.info;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Cross implements Direction {
    UP((source, target) ->
            source.subtractX(target) == 0 && !source.isSubtractYPositive(target),
            new int[]{0, 1}),
    DOWN((source, target) ->
            source.subtractX(target) == 0 && source.isSubtractYPositive(target),
            new int[]{0, -1}),
    RIGHT((source, target) ->
            !source.isSubtractXPositive(target) && source.subtractY(target) == 0,
            new int[]{1, 0}),
    LEFT((source, target) ->
            source.isSubtractXPositive(target) && source.subtractY(target) == 0,
            new int[]{-1, 0});

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
