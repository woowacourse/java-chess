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
        int startOrdinal = Math.min(from.ordinal(), to.ordinal()) + 1;
        int endOrdinal = Math.max(from.ordinal(), to.ordinal());

        return IntStream.range(startOrdinal, endOrdinal)
                .mapToObj(ordinal -> getByValue(ordinal + 1))
                .collect(Collectors.toList());
    }

    int subtract(XAxis other) {
        return this.value - other.value;
    }
}
