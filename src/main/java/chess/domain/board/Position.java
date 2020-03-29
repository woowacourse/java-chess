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

    public static int rowGap(Position start, Position end) {
        return Math.abs(start.row() - end.row());
    }

    public static int columnGap(Position start, Position end) {
        return Math.abs(start.column() - end.column());
    }

    public boolean isOn(final Row row) {
        return this.row == row;
    }

    public boolean isOn(final Column column) {
        return this.column == column;
    }

    public boolean isOnSameLineWith(final Position other) {
        return Position.columnGap(this, other) == 0
            || Position.rowGap(this, other) == 0
            || Position.columnGap(this, other) == Position.rowGap(this, other);
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    public int row() {
        return row.getValue();
    }

    public int column() {
        return column.getValue();
    }

    public boolean inBetween(final Position start, final Position end) {
        Row startRow = start.getRow();
        Row endRow = end.getRow();
        Column startColumn = start.getColumn();
        Column endColumn = end.getColumn();
        if (isOn(startRow) && isOn(endRow)
            && isMiddle(column(), start.column(), end.column())) {
            return true;
        }
        if (isOn(startColumn) && isOn(endColumn)
            && isMiddle(row(), start.row(), end.row())) {
            return true;
        }
        return isDiagonalWith(start) && isDiagonalWith(end)
            && isMiddle(column(), start.column(), end.column())
            && isMiddle(row(), start.row(), end.row());
    }

    public boolean isDiagonalWith(final Position other) {
        return Position.rowGap(this, other) == Position.columnGap(this, other);
    }

    private boolean isMiddle(final int middle, final int start, final int end) {
        if (start >= end) {
            return middle >= end && middle <= start;
        }
        return middle <= end && middle >= start;
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
