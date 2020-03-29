package chess.domain.position;

import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.Objects;

public class Position {
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
}
