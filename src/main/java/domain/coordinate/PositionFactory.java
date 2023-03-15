package domain.coordinate;

import java.util.Arrays;
import java.util.function.Function;

public enum PositionFactory {

    ZERO('a', '1', 0),
    ONE('b', '2', 1),
    TWO('c', '3', 2),
    THREE('d', '4', 3),
    FOUR('e', '5', 4),
    FIVE('f', '6', 5),
    SIX('g', '7', 6),
    SEVEN('h', '8', 7);

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
        final char c = command.charAt(0);
        return PositionFactory.from(c, v -> v.x).value;
    }

    private static int findCoordinateY(String command) {
        final char c = command.charAt(1);
        return PositionFactory.from(c, v -> v.y).value;
    }

    private static PositionFactory from(char q, Function<PositionFactory, Character> f) {
        return Arrays.stream(values())
                .filter(value -> f.apply(value) == q)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("범위 내의 값만 입력해주세요."));
    }
}
