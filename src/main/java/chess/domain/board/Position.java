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
        if (isOn(start.getRow()) && isOn(end.getRow())
            && isMiddle(this.column(), start.column(), end.column())) {
            return true;
        }
        if (isOn(start.getColumn()) && isOn(end.getColumn())
            && isMiddle(this.row(), start.row(), end.row())) {
            return true;
        }
        return Position.rowGap(this, start) == Position.columnGap(this, start)
            && Position.rowGap(this, end) == Position.columnGap(this, end)
            && isMiddle(this.column(), start.column(), end.column())
                && isMiddle(this.row(), start.row(), end.row());
    }

    private boolean isMiddle(final int middle, final int start, final int end) {
        if (start >= end) {
            return middle >= end && middle <= start;
        }
        return middle <= end && middle >= start;
    }

    @Override
    public String toString() {
        return column.getName() + row.getName();
    }

    static class PositionCache {
        private static final Map<String, Position> positions = new HashMap<>();

        static {
            for (Row row : Row.values()) {
                loopThroughColumns(row);
            }
        }

        private static Position find(String key) {
            return positions.get(key);
        }

        private static void loopThroughColumns(final Row row) {
            for (Column column : Column.values()) {
                positions.put(key(row, column), new Position(row, column));
            }
        }

        private static String key(final Row row, final Column column) {
            return column.getName() + row.getName();
        }
    }
}
