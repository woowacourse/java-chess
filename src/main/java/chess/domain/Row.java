package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public enum Row {
    EIGHT("8", 8),
    SEVEN("7", 7),
    SIX("6", 6),
    FIVE("5", 5),
    FOUR("4", 4),
    THREE("3", 3),
    TWO("2", 2),
    ONE("1", 1),
    ;

    private static final String INVALID_RANGE = "유효하지 않은 범위입니다.";

    private final String value;
    private final int order;

    Row(String value, int order) {
        this.value = value;
        this.order = order;
    }


    public static Row of(String value) {
        return Arrays.stream(values())
                .filter(row -> row.value.equals(value))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(INVALID_RANGE));
    }

    public static List<Row> initialRows() {
        return List.of(EIGHT, SEVEN, TWO, ONE);
    }

    public static List<Row> getBetween(Row from, Row to) {
        Row maxOrder = getHigherOrder(from, to);
        Row minOrder = getLowerOrder(from, to);

        return Arrays.stream(values())
                .filter(column -> column.order < maxOrder.order && column.order < minOrder.order)
                .collect(Collectors.toList());
    }

    private static Row getHigherOrder(Row from, Row to) {
        if (from.order > to.order) {
            return from;
        }
        return to;
    }

    private static Row getLowerOrder(Row from, Row to) {
        if (from.order < to.order) {
            return from;
        }
        return to;
    }

    public int calculate(Row other) {
        return Math.abs(order - other.order);
    }
}
