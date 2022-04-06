package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum XAxis {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    XAxis(int value) {
        this.value = value;
    }

    public static XAxis getByValue(int value) {
        return Arrays.stream(XAxis.values())
                .filter(xAxis -> xAxis.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 X좌표입니다."));
    }

    public static XAxis getByValue(String value) {
        return getByValue(Integer.parseInt(value));
    }

    public static List<XAxis> getBetween(XAxis from, XAxis to) {
        int startValue = Math.min(from.getValue(), to.getValue());
        int endValue = Math.max(from.getValue(), to.getValue());

        return IntStream.range(startValue + 1, endValue)
                .mapToObj(XAxis::getByValue)
                .collect(Collectors.toList());
    }

    int subtract(XAxis other) {
        return this.value - other.value;
    }

    public int getValue() {
        return value;
    }

    public String getValueAsString() {
        return Integer.toString(value);
    }
}
