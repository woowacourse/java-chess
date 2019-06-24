package chess.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Arrays;

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

    static Square of(Column column, Row row) {
        return sqaurePool.get(column, row);
    }

    @Override
    public String toString() {
        return column.toString() + row.toString();
    }
}
