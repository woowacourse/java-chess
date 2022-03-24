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

    public static List<YAxis> getBetween(YAxis from, YAxis to) {
        int startOrdinal = Math.min(from.ordinal(), to.ordinal()) + 1;
        int endOrdinal = Math.max(from.ordinal(), to.ordinal());

        return IntStream.range(startOrdinal, endOrdinal)
                .mapToObj(ordinal -> getByValue(ordinal + 1))
                .collect(Collectors.toList());
    }

    int subtract(YAxis other) {
        return this.value - other.value;
    }

    public int getValue() {
        return value;
    }
}
