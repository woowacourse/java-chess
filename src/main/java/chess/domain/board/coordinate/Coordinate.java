package chess.domain.board.coordinate;

import chess.domain.direction.Direction;

import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {
    private final Column column;
    private final Row row;
    
    public Coordinate(String[] sourceCoordinate) {
        this(parseColumn(sourceCoordinate), parseRow(sourceCoordinate));
    }
    
    private Coordinate(char column, int row) {
        this(Column.from(column), Row.from(row));
    }
    
    public Coordinate(Column column, Row row) {
        this.column = column;
        this.row = row;
    }
    
    private static char parseColumn(String[] sourceCoordinate) {
        return sourceCoordinate[0].charAt(0);
    }
    
    private static int parseRow(String[] sourceCoordinate) {
        return Integer.parseInt(sourceCoordinate[1]);
    }
    
    public Coordinate nextCoordinate(Direction direction) {
        char nextColumn = column.add(direction.columnDirection());
        int nextRow = row.add(direction.rowDirection());
        return new Coordinate(nextColumn, nextRow);
    }
    
    public boolean canMove(Direction direction) {
        return column.isNextColumnInRange(direction) && row.isNextRowInRange(direction);
    }
    
    public int columnIndex() {
        return column.convertColumnIndex();
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
    
    public Coordinate betweenRow(int rowDirection) {
        Row betweenRow = this.row.betweenRow(rowDirection);
        return new Coordinate(column, betweenRow);
    }
    
    @Override
    public int compareTo(Coordinate otherCoordinate) {
        int rowCompareTo = otherCoordinate.row.compareTo(this.row);
        if (rowCompareTo != 0) {
            return rowCompareTo;
        }
        
        return this.column.compareTo(otherCoordinate.column);
    }
    
    public boolean isLastColumn() {
        return this.column.isLastColumn();
    }
    
    public int row() {
        return row.row();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return column == that.column && row == that.row;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
    
    @Override
    public String toString() {
        return "Coordinate{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
