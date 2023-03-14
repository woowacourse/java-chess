package piece.coordinate;

import java.util.Objects;

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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(row, that.row) && Objects.equals(column, that.column);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
    
    @Override
    public String toString() {
        return "Coordinate{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
