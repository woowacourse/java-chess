package chess.position;

import chess.position.component.Column;
import chess.position.component.Row;

public class Position {
    private Column column;
    private Row row;

    protected Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }
}
