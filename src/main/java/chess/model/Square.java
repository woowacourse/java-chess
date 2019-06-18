package chess.model;

public class Square {
    private final Column column;
    private final Row row;

    public Square(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public Column getColumn() {
        return column;
    }

    public Row getRow() {
        return row;
    }
}
