package chess.domain;

public class Position {

    private Row row;
    private Column column;

    public Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public int getRowIndex() {
        return row.getIndex();
    }

    public int getColumnIndex() {
        return column.getIndex();
    }
}
