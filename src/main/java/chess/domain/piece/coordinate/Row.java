package chess.domain.piece.coordinate;

import java.util.Objects;

public class Row {
    private static final int MIN_ROW = 1;
    private static final int MAX_ROW = 8;
    
    private final int row;
    
    public Row(int row) {
        validateOutOfRange(row);
        this.row = row;
    }
    
    private void validateOutOfRange(int row) {
        if (isOutOfRange(row)) {
            throw new IllegalArgumentException("row는 1~8까지의 숫자만 가능합니다.");
        }
    }
    
    private boolean isOutOfRange(int row) {
        return row < MIN_ROW || row > MAX_ROW;
    }
    
    public int directionNumberTo(Row otherRow) {
        return Integer.compare(otherRow.row, this.row);
    }
    
    public int add(int directionNumber) {
        return row + directionNumber;
    }
    
    public int compareTo(Row otherRow) {
        return this.row - otherRow.row;
    }
    
    public int subtract(Row targetRow) {
        return this.row - targetRow.row;
    }
    
    public boolean isPawnStartRow(int pawnStartRow) {
        return row == pawnStartRow;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row1 = (Row) o;
        return row == row1.row;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(row);
    }
    
    @Override
    public String toString() {
        return "Row{" +
                "row=" + row +
                '}';
    }
}
