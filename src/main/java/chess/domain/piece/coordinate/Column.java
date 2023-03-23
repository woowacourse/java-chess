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
    private static final int FORWARD_DIRECTION_COLUMN = 1;
    private static final int BACK_DIRECTION_COLUMN = -1;
    private static final int STAY_COLUMN = 0;
    
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
        if (this.column > otherColumn.column) {
            return BACK_DIRECTION_COLUMN;
        }
    
        if (this.column < otherColumn.column) {
            return FORWARD_DIRECTION_COLUMN;
        }
    
        return STAY_COLUMN;
    }
    
    public char add(int number) {
        return (char) (column + number);
    }
    
    public int convertColumnIndex() {
        return column - MIN_COLUMN_CHAR;
    }
    
    public int subtract(Column targetColumn) {
        return this.column - targetColumn.column;
    }
    
    @Override
    public String toString() {
        return "Column{" +
                "column=" + column +
                '}';
    }
}
