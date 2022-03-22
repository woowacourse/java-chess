package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Row {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Row(int value) {
        this.value = value;
    }

    public static List<Integer> valuesByDescending() {
        return Arrays.stream(values())
                .sorted((o1, o2) -> o2.value - o1.value)
                .map(Row::getValue)
                .collect(Collectors.toList());
    }

    public int getValue() {
        return value;
    }
}
