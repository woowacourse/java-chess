package chess.domain.piece.coordinate;

import chess.domain.distance.Distances;

import java.util.Objects;

public class Coordinate {
    private final Column column;
    private final Row row;
    
    public Coordinate(String[] sourceCoordinate) {
        this(parseColumn(sourceCoordinate), parseRow(sourceCoordinate));
    }
    
    public Coordinate(char column, int row) {
        this(Column.from(column), Row.from(row));
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
    
    public Coordinate coordinateOneStepFor(Coordinate destinationCoordinate) {
        return new Coordinate(oneStepByColumnFor(destinationCoordinate), oneStepByRowFor(destinationCoordinate));
    }
    
    private char oneStepByColumnFor(Coordinate coordinate) {
        return this.column.add(directionNumberOfColumn(coordinate));
    }
    
    private int directionNumberOfColumn(Coordinate coordinate) {
        return this.column.directionNumberTo(coordinate.column);
    }
    
    private int oneStepByRowFor(Coordinate destinationCoordinate) {
        return this.row.add(directionNumberOfRow(destinationCoordinate));
    }
    
    private int directionNumberOfRow(Coordinate coordinate) {
        return this.row.directionNumberTo(coordinate.row);
    }
    
    public int columnIndex() {
        return column.convertColumnIndex();
    }
    
    public int compareToPieceByRowNum(Coordinate otherCoordinate) {
        return this.row.compareTo(otherCoordinate.row);
    }
    
    public Distances subtractCoordinate(Coordinate otherCoordinate) {
        int subtractedColumnDistance = this.column.subtract(otherCoordinate.column);
        int subtractedRowDistance = this.row.subtract(otherCoordinate.row);
        return new Distances(subtractedColumnDistance, subtractedRowDistance);
    }
    
    public boolean isRowNumLessOrEqualTo(int otherRow) {
        return row.isLessOrEqualTo(otherRow);
    }
    
    public boolean isRowNumOverOrEqualTo(int otherRow) {
        return row.isOverOrEqualTo(otherRow);
    }
    
    public boolean isSameRow(Row otherRow) {
        return row.equals(otherRow);
    }
    
    public int row() {
        return row.row();
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
