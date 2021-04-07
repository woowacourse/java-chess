package chess.domain.position;

import chess.domain.piece.Direction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static List<Position> getPositionCache() {
        return new ArrayList<>(POSITION_CACHE.values());
    }

    public static Position of(String value) {
        return POSITION_CACHE.get(value);
    }

    public static Position of(Column column, Row row) {
        return of(column.getName() + row.getNumber());
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

    public boolean isDiagonal(Position that) {
        return Math.abs(rowGap(that)) == Math.abs(columnGap(that));
    }

    public int columnGap(Position that) {
        return Math.abs(column() - that.column());
    }

    public int rowGap(Position that) {
        return Math.abs(row() - that.row());
    }

    public boolean canGoTo(Direction direction) {
        return column.isInRange(direction.columnValue()) && row.isInRange(direction.rowValue());
    }

    public Position moveTo(Direction direction) {
        Column newColumn = column.move(direction);
        Row newRow = row.move(direction);
        return Position.of(newColumn, newRow);
    }

    public boolean isOn(Column column) {
        return this.column.equals(column);
    }

    public boolean isUpperTo(Position that) {
        return row() > that.row();
    }

    public boolean isLowerTo(Position that) {
        return row() < that.row();
    }

    public int row() {
        return row.getValue();
    }

    public int column() {
        return column.getValue();
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
}
