package chess.domain.position;

import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

public class Position {
    private Column column;
    private Row row;

    protected Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }
}
