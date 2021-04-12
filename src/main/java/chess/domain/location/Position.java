package chess.domain.location;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    public static Collection<Position> all() {
        return cache.values();
    }

    public boolean canMove(Vector vector) {
        return column.canMove(vector.column()) && row.canMove(vector.row());
    }

    public boolean canMove(int column, int row) {
        return this.column.canMove(column) && this.row.canMove(row);
    }

    public Position move(Vector vector) {
        return Position.of(column.move(vector.column()), row.move(vector.row()));
    }

    public Position move(int column, int row) {
        return Position.of(this.column.move(column), this.row.move(row));
    }

    public boolean hasRow(Row row) {
        return this.row.equals(row);
    }

    public String toKey() {
        return column.value() + row.value();
    }
}
