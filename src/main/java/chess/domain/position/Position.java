package chess.domain.position;

import chess.domain.piece.strategy.Direction;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> POSITION_CACHE = new HashMap<>();

    static {
        positionCache();
    }

    private final Column column;
    private final Row row;

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    private Position(String column, String row) {
        this(Column.getColumn(column), Row.getRow(row));
    }

    public static Position ofName(String value) {
        return POSITION_CACHE.get(value);
    }

    public static Position ofColumnAndRow(Column column, Row row) {
        return ofName(column.getName() + row.getNumber());
    }

    private static void positionCache() {
        columnCache();
    }

    private static void columnCache() {
        for (Column column : Column.values()) {
            rowCache(column);
        }
    }

    private static void rowCache(Column column) {
        for (Row row : Row.values()) {
            POSITION_CACHE.put(column.getName() + row.getNumber(), new Position(column, row));
        }
    }

    public boolean isWhitePawnStartLine() {
        return row.equals(Row.TWO);
    }

    public boolean isBlackPawnStartLine() {
        return row.equals(Row.SEVEN);
    }

    public boolean isStraight(Position that) {
        return columnGap(that) == 0 || rowGap(that) == 0;
    }

    public boolean isDiagonal(Position that) {
        return Math.abs(rowGap(that)) == Math.abs(columnGap(that));
    }

    public boolean isKnightPath(Position that) {
        return Math.pow(rowGap(that), 2) + Math.pow(columnGap(that), 2) == 5;
    }

    public int columnGap(Position that) {
        return Math.abs(column() - that.column());
    }

    public int rowGap(Position that) {
        return Math.abs(row() - that.row());
    }

    public int row() {
        return row.getValue();
    }

    public int column() {
        return column.getValue();
    }

    public boolean isBlockedWhenGoTo(Direction direction) {
        return column.isBoundary(direction) || row.isBoundary(direction);
    }

    public String name() {
        return this.column.getName() + this.row.getNumber();
    }

    @Override
    public String toString() {
        return column.getName() + row.getNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return row == position.row &&
                column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    public Position moveTo(Direction direction) {
        Column newColumn = column.move(direction);
        Row newRow = row.move(direction);
        return Position.ofColumnAndRow(newColumn, newRow);
    }

    public boolean isOn(Column column) {
        return this.column.equals(column);
    }
}
