package coordinate;

public class Coordinate {
    private final Row row;
    private final Column column;
    
    public Coordinate(int row, char column) {
        this(new Row(row), new Column(column));
    }
    
    private Coordinate(Row row, Column column) {
        this.row = row;
        this.column = column;
    }
}
