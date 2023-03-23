package chess.piece.coordinate;

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
        return Integer.compare(otherCoordinate.row.ordinal(),this.row.ordinal());
    }

    public int compareByColumn(Coordinate otherCoordinate){
        System.out.println("otherCorNum : " +otherCoordinate.column.ordinal());
        System.out.println("thisCorNum : "+this.column.ordinal());
        return Integer.compare(otherCoordinate.column.ordinal(),this.column.ordinal());
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
        return column.ordinal();
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
