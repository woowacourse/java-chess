package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum YAxis {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    YAxis(int value) {
        this.value = value;
    }

    public static YAxis getByValue(int value) {
        return Arrays.stream(YAxis.values())
                .filter(yAxis -> yAxis.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Y좌표입니다."));
    }

    public static YAxis getByValue(String value) {
        return getByValue(Integer.parseInt(value));
    }

    public static List<YAxis> getBetween(YAxis from, YAxis to) {
        int startValue = Math.min(from.getValue(), to.getValue());
        int endValue = Math.max(from.getValue(), to.getValue());

        return IntStream.range(startValue + 1, endValue)
                .mapToObj(YAxis::getByValue)
                .collect(Collectors.toList());
    }

    int subtract(YAxis other) {
        return this.value - other.value;
    }

    public int getValue() {
        return value;
    }

    public String getValueAsString() {
        return Integer.toString(value);
    }
}
