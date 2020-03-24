package chess.domain.position;

import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

public class Position {
    private Row row;
    private Column column;

    protected Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }
}
