package domain.coordinate;

import java.util.Arrays;
import java.util.function.Function;

public enum PositionFactory {

    ZERO('a', '8', 0),
    ONE('b', '7', 1),
    TWO('c', '6', 2),
    THREE('d', '5', 3),
    FOUR('e', '4', 4),
    FIVE('f', '3', 5),
    SIX('g', '2', 6),
    SEVEN('h', '1', 7);

    private static final int INDEX_X = 0;
    private static final int INDEX_Y = 1;

    private final char x;
    private final char y;
    private final int value;

    PositionFactory(char x, char y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public static Position createPosition(String command) {
        return Position.of(findCoordinateX(command), findCoordinateY(command));
    }

    private static int findCoordinateX(String command) {
        final char coordinate = command.charAt(INDEX_X);
        return PositionFactory.from(coordinate, value -> value.x).value;
    }

    private static int findCoordinateY(String command) {
        final char coordinate = command.charAt(INDEX_Y);
        return PositionFactory.from(coordinate, value -> value.y).value;
    }

    private static PositionFactory from(char coordinate, Function<PositionFactory, Character> findCoordinate) {
        return Arrays.stream(values())
                .filter(value -> findCoordinate.apply(value) == coordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("범위 내의 값만 입력해주세요."));
    }

}
