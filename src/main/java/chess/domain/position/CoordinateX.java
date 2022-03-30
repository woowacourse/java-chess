package chess.domain.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum CoordinateX {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8),
    ;

    private static final int ONE_STEP = 1;

    private final String name;
    private final int order;

    CoordinateX(final String name, final int order) {
        this.name = name;
        this.order = order;
    }

    public static CoordinateX from(final int order) {
        return CoordinateX.from(getName(order));
    }

    public static CoordinateX from(final String other) {
        return Arrays.stream(CoordinateX.values())
                .filter(coordinateX -> coordinateX.name.equals(other))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판 범위를 벗어납니다."));
    }

    public static List<CoordinateX> sorted() {
        return Arrays.stream(CoordinateX.values())
                .sorted(Comparator.comparing(CoordinateX::getOrder))
                .collect(Collectors.toUnmodifiableList());
    }

    public CoordinateX next(final CoordinateX to) {
        if (this == to) {
            return to;
        }
        int nextStep = getNextStep(this, to);
        return Arrays.stream(CoordinateX.values())
                .filter(coordinateX -> this.order + nextStep == coordinateX.order)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판 범위를 벗어납니다."));
    }

    private int getNextStep(final CoordinateX from, final CoordinateX to) {
        if (to.order > from.order) {
            return ONE_STEP;
        }
        return -ONE_STEP;
    }

    public int getOrder() {
        return order;
    }

    private static String getName(final int order) {
        return Arrays.stream(CoordinateX.values())
                .filter(coordinateX -> coordinateX.order == order)
                .findFirst()
                .map(coordinateX -> coordinateX.name)
                .orElseThrow(() -> new IllegalArgumentException("체스판 범위를 벗어납니다."));
    }
}
