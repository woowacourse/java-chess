package chess.domain.position;

import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.Objects;

public class Position implements Comparable<Position> {
    private final Row row;
    private final Column column;

    protected Position(Row row, Column column) {
        Objects.requireNonNull(row);
        Objects.requireNonNull(column);

        this.row = row;
        this.column = column;
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    @Override
    public int compareTo(Position o) {
        int rowCompare = row.compareTo(o.getRow());
        if (rowCompare != 0) {
            return rowCompare;
        }
        return column.compareTo(o.getColumn());
    }
}
