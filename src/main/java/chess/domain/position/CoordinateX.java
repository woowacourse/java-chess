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

    private final String name;
    private final int order;

    CoordinateX(final String name, final int order) {
        this.name = name;
        this.order = order;
    }

    public static CoordinateX from(final int other) {
        return CoordinateX.from(getName(other));
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

    public static int difference(final CoordinateX from, final CoordinateX to) {
        return Math.abs(from.order - to.order);
    }

    public static int min(final CoordinateX from, final CoordinateX to) {
        return Math.min(from.order, to.order);
    }

    public static int max(final CoordinateX from, final CoordinateX to) {
        return Math.max(from.order, to.order);
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
