package chess.piece.coordinate;

import java.util.Objects;

public class Column {
    private static final char MIN_COLUMN_CHAR = 'a';
    private static final char MAX_COLUMN_CHAR = 'h';
    
    private final char column;
    
    public Column(char column) {
        validateOutOfRange(column);
        this.column = column;
    }
    
    private void validateOutOfRange(char column) {
        if (column < MIN_COLUMN_CHAR || column > MAX_COLUMN_CHAR) {
            throw new IllegalArgumentException("column는 a~h까지의 문자만 가능합니다.");
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column1 = (Column) o;
        return column == column1.column;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(column);
    }
    
    @Override
    public String toString() {
        return "Column{" +
                "column=" + column +
                '}';
    }
}
