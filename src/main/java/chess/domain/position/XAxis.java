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

    public static List<XAxis> getBetween(XAxis from, XAxis to) {
        int startXPosition = Math.min(from.value, to.value) + 1;
        int endXPosition = Math.max(from.value, to.value);

        return IntStream.range(startXPosition, endXPosition)
                .mapToObj(XAxis::getByValue)
                .collect(Collectors.toList());
    }

    public int getValue() {
        return value;
    }
}
