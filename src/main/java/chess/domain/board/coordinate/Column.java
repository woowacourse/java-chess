package chess.domain.board.coordinate;

import chess.domain.direction.Direction;

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
    
    public boolean isNextColumnInRange(Direction direction) {
        int nextColumn = this.column + direction.columnDirection();
        return Arrays.stream(values())
                .anyMatch(perColumn -> perColumn.isSameColumn((char) nextColumn));
    }
    
    public char add(int number) {
        return (char) (column + number);
    }
    
    public int convertColumnIndex() {
        return column - MIN_COLUMN_CHAR;
    }
    
    public boolean isLastColumn() {
        return this == H;
    }
    
    @Override
    public String toString() {
        return "Column{" +
                "column=" + column +
                '}';
    }
}
