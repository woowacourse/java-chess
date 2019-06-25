package chess.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Arrays;
import java.util.Objects;

public class Square {
    private static final Table<Column, Row, Square> sqaurePool = HashBasedTable.create();

    static {
        for (Column column : Column.values()) {
            Arrays.stream(Row.values()).forEach(row -> sqaurePool.put(column, row, new Square(column, row)));
        }
    }

    private Column column;
    private Row row;

    private Square(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Square of(Column column, Row row) {
        return sqaurePool.get(column, row);
    }

    boolean hasNext(Direction direction) {
        return column.hasNext(direction) && row.hasNext(direction);
    }

    Square next(Direction direction) {
        return of(column.next(direction), row.next(direction));
    }

    public boolean isAtColumn(Column column) {
        return this.column.equals(column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return column == square.column &&
                row == square.row;
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