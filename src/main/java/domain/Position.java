package domain;

import java.util.*;

public class Position {
    private final Column column;
    private final Row row;
    private static final Map<Column, Map<Row, Position>> cache = new HashMap<>();

    static {
        for (Column x : Column.values()) {
            cache.put(x, getCacheByColumn(x));
        }
    }

    private static Map<Row, Position> getCacheByColumn(Column x) {
        Map<Row, Position> cacheByColumn = new HashMap<>();
        for (Row y : Row.values()) {
            cacheByColumn.put(y, new Position(x, y));
        }
        return cacheByColumn;
    }

    public static Position of(Column column, Row row) {
        if (cache.containsKey(column) && cache.get(column).containsKey(row)) {
            return cache.get(column).get(row);
        }
        return new Position(column, row);
    }

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }


    public Position moveBy(int columnValue, int rowValue) {
        return Position.of(column.moveBy(columnValue), row.moveBy(rowValue));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
