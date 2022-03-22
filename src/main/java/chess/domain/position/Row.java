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

    public static Row of(final int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("'" + value + "'는 올바르지 않은 로우입니다."));
    }

    public int getValue() {
        return value;
    }
}
