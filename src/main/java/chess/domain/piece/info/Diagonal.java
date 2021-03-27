package chess.domain.piece.info;


import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Diagonal implements Direction {
    UP_RIGHT((source, target) ->
            !source.isSubtractXPositive(target) && !source.isSubtractYPositive(target),
            new int[]{1, 1}),
    UP_LEFT((source, target) ->
            source.isSubtractXPositive(target) && !source.isSubtractYPositive(target),
            new int[]{-1, 1}),
    DOWN_RIGHT((source, target) ->
            !source.isSubtractXPositive(target) && source.isSubtractYPositive(target),
            new int[]{1, -1}),
    DOWN_LEFT((source, target) ->
            source.isSubtractXPositive(target) && source.isSubtractYPositive(target),
            new int[]{-1, -1});

    private final BiPredicate<Position, Position> findDiagonal;
    private final int[] changeValues;

    Diagonal(BiPredicate<Position, Position> findDiagonal, int[] changeValues) {
        this.findDiagonal = findDiagonal;
        this.changeValues = changeValues;
    }

    public static Diagonal findDiagonalByTwoPosition(Position source, Position target) {
        return Arrays.stream(values())
                .filter(value -> source.isDiagonal(target))
                .filter(value -> value.findDiagonal.test(source, target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(DIRECTION_ERROR));
    }

    @Override
    public int[] getChangeValues() {
        return changeValues;
    }
}
