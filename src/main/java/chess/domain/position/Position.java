package chess.domain.position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: 2020/03/25 패키지 옮길수도 있음
public class Position {
    private static final Map<String, Position> POSITIONS = new HashMap<>();

    static {
        for (Column column : Column.values()) {
            putByRow(column);
        }
    }

    private static void putByRow(Column column) {
        for (Row row : Row.values()) {
            POSITIONS.put(key(column, row), new Position(column, row));
        }
    }

    private final Column column;
    private final Row row;

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(Column column, Row row) {
        return POSITIONS.get(key(column, row));
    }

    public static Position of(String position) {
        return POSITIONS.get(position);
    }

    private static String key(Column column, Row row) {
        return column.getName() + row.getName();
    }

    public Position up() {
        return Position.of(column, row.plus());
    }

    public Position down() {
        return Position.of(column, row.minus());
    }

    public Position right() {
        return Position.of(column.plus(), row);
    }

    public Position left() {
        return Position.of(column.minus(), row);
    }

    public Position reverse() {
        return Position.of(column.reverse(), row.reverse());
    }

    public boolean isColumnEquals(Position that) {
        return column.equals(that.column);
    }

    public boolean isRowEquals(Position that) {
        return row.equals(that.row);
    }

    public int getColumnGap(Position that) {
        return column.compareTo(that.column);
    }

    public int getRowGap(Position that) {
        return row.compareTo(that.row);
    }

    public Column getColumn() {
        return column;
    }

    public Row getRow() {
        return row;
    }

    public String getName() {
        return column.getName() + row.getName();
    }

    public static String getReversedNameOf(String name) {
        return of(name).reverse().getName();
    }

    public static List<Position> getPositions() {
        return List.copyOf(POSITIONS.values());
    }
}