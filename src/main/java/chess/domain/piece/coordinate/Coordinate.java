package chess.domain.piece.coordinate;

import java.util.List;
import java.util.Objects;

public class Coordinate {
    private final Column column;
    private final Row row;
    
    public Coordinate(String[] sourceCoordinate) {
        this(parseColumn(sourceCoordinate), parseRow(sourceCoordinate));
    }
    
    public Coordinate(char column, int row) {
        this(new Column(column), new Row(row));
    }
    
    private Coordinate(Column column, Row row) {
        this.column = column;
        this.row = row;
    }
    
    private static char parseColumn(String[] sourceCoordinate) {
        return sourceCoordinate[0].charAt(0);
    }
    
    private static int parseRow(String[] sourceCoordinate) {
        return Integer.parseInt(sourceCoordinate[1]);
    }
    
    public int compareToPieceByRowNum(Coordinate otherCoordinate) {
        return this.row.compareTo(otherCoordinate.row);
    }
    
    public List<Integer> calculateCoordinateDistance(Coordinate otherCoordinate) {
        int columnDistance = this.column.subtract(otherCoordinate.column);
        int rowDistance = this.row.subtract(otherCoordinate.row);
        return List.of(columnDistance, rowDistance);
    }
    
    public boolean isPawnStartRow(int pawnStartRow) {
        return row.isPawnStartRow(pawnStartRow);
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
