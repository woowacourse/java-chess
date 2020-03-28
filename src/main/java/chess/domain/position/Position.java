package chess.domain.position;

import chess.domain.route.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    private Column column;
    private Row row;

    private static final Map<String, Position> cache = new HashMap<>();

    static {
        for (Column column : Column.values()) {
            createCacheByRank(cache, column);
        }
    }

    private static void createCacheByRank(Map<String, Position> cache, Column column) {
        for (Row row : Row.values()) {
            cache.put(key(column, row), new Position(column, row));
        }
    }

    private static String key(Column column, Row row) {
        return column.toString() + row.toString();
    }

    public static Position of(String key) {
        Position position = cache.get(key);

        if (position == null) {
            throw new IllegalArgumentException("존재하지 않는 위치입니다.");
        }

        return position;
    }

    public static Position of(Column column, Row row) {
        return of(column.toString() + row.toString());
    }

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public boolean isAt(Row row) {
        return this.row == row;
    }

    public boolean isAt(Column column) {
        return this.column == column;
    }

    public Position moveTo(Direction direction) {
        Column movedColumn = column.plus(direction.getXDegree());
        Row movedRow = row.plus(direction.getYDegree());
        return new Position(movedColumn, movedRow);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return column == position.column &&
                row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return column.toString() + row.toString();
    }
}
