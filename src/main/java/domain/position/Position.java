package domain.position;

import java.util.*;
import java.util.stream.Collectors;

public class Position {
    private final Column column;
    private final Row row;
    private static final Map<String, Position> cache = new HashMap<>();

    static {
        for (Column x : Column.values()) {
            cacheByColumn(x);
        }
    }

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(Column column, Row row) {
        return from(toKey(column, row));
    }

    public static Position from(String key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        throw new IllegalArgumentException();
    }

    private static void cacheByColumn(Column x) {
        for (Row y : Row.values()) {
            cache.put(toKey(x, y), new Position(x, y));
        }
    }

    private static String toKey(final Column column, final Row row) {
        return column.value() + row.value();
    }

    public static List<Position> all() {
        return cache.values()
                    .stream()
                    .collect(Collectors.toList());
    }

    public Position moveBy(int columnValue, int rowValue) {
        return Position.of(column.moveBy(columnValue), row.moveBy(rowValue));
    }

    public boolean isOrthogonal(Position to) {
        return hasRow(to.row) || hasColumn(to.column);
    }

    public boolean hasRow(Row row) {
        return this.row.equals(row);
    }

    private boolean hasColumn(Column column) {
        return this.column.equals(column);
    }

    public List<Position> getBetween(Position to) {
        List<Position> betweenPosition = new ArrayList<>();
        if (hasColumn(to.column)) {
            betweenPosition.addAll(row.getBetween(to.row)
                                      .stream()
                                      .map(row -> Position.of(column, row))
                                      .collect(Collectors.toList()));
        }

        if (hasRow(to.row)) {
            betweenPosition.addAll(column.getBetween(to.column)
                                         .stream()
                                         .map(column -> Position.of(column, row))
                                         .collect(Collectors.toList()));
        }
        return betweenPosition;
    }
}
