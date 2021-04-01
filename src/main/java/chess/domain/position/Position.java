package chess.domain.position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Position {
    private static final Map<String, Position> CACHE = new HashMap<>();
    public static final String OVER_RANGE_ERROR = "범위를 넘어가는 move 입니다";
    private final Column column;
    private final Row row;

    static {
        for (final Column x : Column.values()) {
            cacheByColumn(x);
        }
    }

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(final Column column, final Row row) {
        return from(toKey(column, row));
    }

    public static Position from(final String key) {
        if (CACHE.containsKey(key)) {
            return CACHE.get(key);
        }
        throw new IllegalArgumentException(OVER_RANGE_ERROR);
    }

    private static void cacheByColumn(final Column x) {
        for (final Row y : Row.values()) {
            CACHE.put(toKey(x, y), new Position(x, y));
        }
    }

    private static String toKey(final Column column, final Row row) {
        return column.value() + row.value();
    }

    public Position moveBy(final int columnValue, final int rowValue) {
        return Position.of(column.moveBy(columnValue), row.moveBy(rowValue));
    }

    public boolean isOrthogonal(final Position to) {
        return hasRow(to.row) || hasColumn(to.column);
    }

    public boolean isDiagonal(final Position to) {
        return Math.abs(row.diff(to.row)) == Math.abs(column.diff(to.column));
    }

    public boolean hasRow(final Row row) {
        return this.row.equals(row);
    }

    private boolean hasColumn(final Column column) {
        return this.column.equals(column);
    }

    public List<Position> getBetween(final Position to) {
        final List<Position> betweenPosition = new ArrayList<>();
        if (hasColumn(to.column)) {
            betweenPosition.addAll(betweenPositionsHasSameColumn(to));
        }
        if (hasRow(to.row)) {
            betweenPosition.addAll(betweenPositionsHasSameRow(to));
        }
        if (isDiagonal(to)) {
            betweenPosition.addAll(betweenPositionsByDiagonal(to));
        }
        return betweenPosition;
    }

    private List<Position> betweenPositionsHasSameColumn(final Position to) {
        return row.getBetween(to.row)
                  .stream()
                  .map(row -> Position.of(column, row))
                  .collect(Collectors.toList());
    }

    private List<Position> betweenPositionsHasSameRow(final Position to) {
        return column.getBetween(to.column)
                     .stream()
                     .map(column -> Position.of(column, row))
                     .collect(Collectors.toList());
    }

    private List<Position> betweenPositionsByDiagonal(final Position to) {
        final List<Position> betweenPosition = new ArrayList<>();
        final int rowDirection = row.unitDirection(to.row);
        final int columnDirection = column.unitDirection(to.column);
        Position temp = Position.of(column, row);
        while (!temp.equals(to)) {
            temp = temp.moveBy(columnDirection, rowDirection);
            betweenPosition.add(temp);
        }
        return betweenPosition;
    }

    public int diffRow(final Position to) {
        return row.diff(to.row);
    }

    public int diffColumn(final Position to) {
        return column.diff(to.column);
    }

    public Column column() {
        return column;
    }

    public Row row() {
        return row;
    }
}
