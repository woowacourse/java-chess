package chess.domain.board;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Position {

    private final Row row;
    private final Column column;

    private Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public static Collection<Position> getAllPositions() {
        return PositionCache.positions.values();
    }

    public static Position of(final Row row, final Column column) {
        return PositionCache.find(PositionCache.key(row, column));
    }

    public static Position of(String value) {
        return PositionCache.find(value);
    }

    public boolean isOn(final Row row) {
        return this.row == row;
    }

    public boolean isOn(final Column column) {
        return this.column == column;
    }

    public boolean isOnSameRowOrColumnWith(final Position other) {
        return isOn(other.row) || isOn(other.column);
    }

    public boolean isOnSameLineWith(final Position other) {
        return columnGap(other) == 0
            || rowGap(other) == 0
            || this.isDiagonalWith(other);
    }

    public double distanceSquaredWith(final Position other) {
        return Math.pow(rowGap(other), 2) + Math.pow(columnGap(other), 2);
    }

    public boolean existsBetween(final Position start, final Position end) {
        if (start.isOnSameLineWith(end)) {
            return inBetween(start, end);
        }
        Position middle = Position.of(start.row, end.column);
        return inBetween(start, middle) || inBetween(end, middle);
    }

    public boolean inBetween(final Position start, final Position end) {
        if (isOn(start.row) && isOn(end.row)
            && column.inBetween(start.column, end.column)) {
            return true;
        }
        if (isOn(start.column) && isOn(end.column)
            && row.inBetween(start.row, end.row)) {
            return true;
        }
        return isDiagonalWith(start) && isDiagonalWith(end)
            && column.inBetween(start.column, end.column)
            && row.inBetween(start.row, end.row);
    }

    public boolean isDiagonalWith(final Position other) {
        return rowGap(other) == columnGap(other);
    }

    public boolean isGreaterThan(final Position other) {
        return row() > other.row();
    }

    public int row() {
        return row.getValue();
    }

    private int column() {
        return column.getValue();
    }

    private int rowGap(Position other) {
        return Math.abs(row() - other.row());
    }

    private int columnGap(Position other) {
        return Math.abs(column() - other.column());
    }

    @Override
    public String toString() {
        return PositionCache.key(row, column);
    }

    private static class PositionCache {
        private static final Map<String, Position> positions = new HashMap<>();

        static {
            for (Row row : Row.values()) {
                loopThroughColumns(row);
            }
        }

        private static void loopThroughColumns(final Row row) {
            for (Column column : Column.values()) {
                positions.put(key(row, column), new Position(row, column));
            }
        }

        private static Position find(String key) {
            return positions.get(key);
        }

        private static String key(final Row row, final Column column) {
            return column.getName() + row.getName();
        }
    }
}
