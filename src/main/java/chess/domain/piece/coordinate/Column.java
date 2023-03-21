package chess.domain.piece.coordinate;

import java.util.Arrays;

public enum Column {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');
    
    private static final char MIN_COLUMN_CHAR = 'a';
    
    private final char column;
    
    Column(char column) {
        this.column = column;
    }
    
    public static Column from(char otherColumn) {
        return Arrays.stream(values())
                .filter(column -> column.isSameColumn(otherColumn))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Column는 a~h까지의 문자만 가능합니다. 다시 입력해주세요."));
    }
    
    private boolean isSameColumn(char otherColumn) {
        return this.column == otherColumn;
    }
    
    public int directionNumberTo(Column otherColumn) {
        return Integer.compare(otherColumn.column, column);
    }
    
    public char add(int directionNumber) {
        return (char) (column + directionNumber);
    }
    
    public int minusMinColumn() {
        return column - MIN_COLUMN_CHAR;
    }
    
    public int subtract(Column targetColumn) {
        return this.column - targetColumn.column;
    }
    
    public int columnIndex() {
        return column - 'a';
    }
    
    @Override
    public String toString() {
        return "Column{" +
                "column=" + column +
                '}';
    }
}
