package chess.domain;

public class Position {

    private Row row;
    private Column column;

    public Position(int row, int column){
        this(Row.getRow(row), Column.getColumn(column));
    }

    public Position(Row row, Column column){
        this.row = row;
        this.column = column;
    }

    public static Position of(int row, int column) {
        return new Position(row, column);
    }
}
