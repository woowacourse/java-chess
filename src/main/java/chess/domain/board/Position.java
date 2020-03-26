package chess.domain.board;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Position {
    private static final Map<String, Position> positions = new HashMap<>();

    private final Row row;
    private final Column column;

    private Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(final Row row, final Column column) {
        return positions.get(key(row, column));
    }

    public static Position of(String value) {
        return positions.get(value);
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

    public static Collection<Position> getAllPositions() {
        return positions.values();
    }

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

    private static String key(final Row row, final Column column) {
        return column.getName() + row.getValue();
    }

    public boolean inBetween(final Position start, final Position end) {
        if (isOn(start.getRow()) && isOn(end.getRow())
            && isBetweenComparingInt(this.column(), start.column(), end.column())) {
            return true;
        }
        if (isOn(start.getColumn()) && isOn(end.getColumn())
            && isBetweenComparingInt(this.row(), start.row(), end.row())) {
            return true;
        }
        if (Position.rowGap(this, start) == Position.columnGap(this, start)
            && Position.rowGap(this, end) == Position.columnGap(this, end)
            && isBetweenComparingInt(this.column(), start.column(), end.column())
            && isBetweenComparingInt(this.row(), start.row(), end.row())) {
            return true;
        }
        return false;
    }

    private boolean isBetweenComparingInt(int middle, int start, int end) {
        if (start >= end) {
            return middle >= end
                && middle <= start;
        }
        return middle <= end
            && middle >= start;
    }
}
