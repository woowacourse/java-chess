package chess.domain.position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public List<Position> positionsOfDirection(int columnValue, int rowValue) {
        List<Position> positions = new ArrayList<>();
        Position temp = this;
        while (temp.canMove(columnValue, rowValue)) {
            temp = temp.move(columnValue, rowValue);
            positions.add(temp);
        }
        return positions;
    }

    private boolean canMove(int columnValue, int rowValue) {
        return column.canMove(columnValue) && row.canMove(rowValue);
    }

    public Position move(int columnValue, int rowValue) {
        return Position.of(column.move(columnValue), row.move(rowValue));
    }

    public boolean hasRow(Row row) {
        return this.row.equals(row);
    }

}
