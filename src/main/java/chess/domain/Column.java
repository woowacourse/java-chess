package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public enum Column {
    A("A", 1),
    B("B", 2),
    C("C", 3),
    D("D", 4),
    E("E", 5),
    F("F", 6),
    G("G", 7),
    H("H", 8),
    ;

    private static final String INVALID_RANGE = "유효하지 않은 범위입니다.";

    private final String value;
    private final int order;

    Column(String value, int order) {
        this.value = value;
        this.order = order;
    }

    public static Column of(String value) {
        return Arrays.stream(values())
            .filter(column -> column.value.equalsIgnoreCase(value))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException(INVALID_RANGE));
    }

    public static List<Column> getBetween(Column from, Column to) {
        Column maxOrder = getHigherOrder(from, to);
        Column minOrder = getLowerOrder(from, to);

        return Arrays.stream(values())
            .filter(column -> column.order < maxOrder.order && column.order < minOrder.order)
            .collect(Collectors.toList());
    }

    private static Column getHigherOrder(Column from, Column to) {
        if (from.order > to.order) {
            return from;
        }
        return to;
    }

    private static Column getLowerOrder(Column from, Column to) {
        if (from.order < to.order) {
            return from;
        }
        return to;
    }

    public int calculate(Column other) {
        return Math.abs(order - other.order);
    }
}
