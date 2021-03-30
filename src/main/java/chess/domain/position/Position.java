package chess.domain.position;

import chess.domain.piece.Direction;

import java.util.*;

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

    public List<Position> positionsOf(Direction direction) {
        List<Position> positions = new ArrayList<>();
        Position temp = this;
        while (temp.canMove(direction)) {
            temp = temp.move(direction);
            positions.add(temp);
        }
        return positions;
    }

    public boolean canMove(Direction direction) {
        return column.canMove(direction.column()) && row.canMove(direction.row());
    }

    public Position move(Direction direction) {
        return Position.of(column.move(direction.column()), row.move(direction.row()));
    }

    public boolean hasRow(Row row) {
        return this.row.equals(row);
    }
}
