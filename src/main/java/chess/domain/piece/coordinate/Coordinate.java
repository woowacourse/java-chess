package chess.domain.piece.coordinate;

import java.util.List;
import java.util.Objects;

public class Coordinate {
    private final Row row;
    private final Column column;


    public Coordinate(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Coordinate createCoordinate(String row, String column) {
        return new Coordinate(Row.from(row), Column.fromName(column));
    }

    public int compareByRowNum(Coordinate otherCoordinate) {
        return this.row.compareIndex(otherCoordinate.row);
    }

    public int compareByColumn(Coordinate otherCoordinate){
        return this.column.compareIndex(otherCoordinate.column);
    }
    
    public List<Integer> calculateCoordinateDistance(Coordinate otherCoordinate) {
        int columnDistance = this.column.subtract(otherCoordinate.column);
        int rowDistance = this.row.subtract(otherCoordinate.row);
        return List.of(columnDistance, rowDistance);
    }
    
    public boolean isPawnStartRow() {
        return row.isPawnStartRow();
    }


    public Coordinate move(int rowAdd, int columnAdd){
        return new Coordinate(this.row.up(rowAdd),this.column.up(columnAdd));
    }

    public int columnIndex(){
        return column.realFieldIndex();
    }

    public int compareTo(Coordinate otherCoordinate){
        return this.row.compareTo(otherCoordinate.row);
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
