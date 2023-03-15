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
        int rowDistance = this.row.subtract(targetCoordinate.row);
        int columnDistance = this.column.subtract(targetCoordinate.column);
        return List.of(rowDistance, columnDistance);
    }
    
    public boolean isBlackPawnStartRow() {
        return row.isBlackPawnStartRow();
    }
    
    public boolean isWhitePawnStartRow() {
        return row.isWhitePawnStartRow();
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
