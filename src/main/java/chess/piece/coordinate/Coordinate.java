package chess.piece.coordinate;

import java.util.List;
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
    
    public int compareToPieceByRowNum(Coordinate otherCoordinate) {
        return this.row.compareTo(otherCoordinate.row);
    }
    
    public List<Integer> calculateCoordinateDistance(Coordinate targetCoordinate) {
        int columnDistance = this.column.subtract(targetCoordinate.column);
        int rowDistance = this.row.subtract(targetCoordinate.row);
        return List.of(columnDistance, rowDistance);
    }
    
    public boolean isPawnStartRow(int startRow) {
        return row.isPawnStartRow(startRow);
    }
    
    public boolean isSameColumn(char otherColumn) {
        return this.column.isSame(otherColumn);
    }
    
    public boolean isSameRow(int otherRow) {
        return this.row.isSame(otherRow);
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
