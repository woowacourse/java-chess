package chess.domain.position;

import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

public class Position {
    private final Row row;
    private final Column column;

    protected Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    public static String key(Position position) {
        return key(position.getRow(), position.getColumn());
    }

    public static String key(Row row, Column column) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(row.getValue());
        stringBuffer.append(column.getValue());
        return stringBuffer.toString();
    }
}
