package chess.domain.piece.coordinate;

import java.util.Arrays;

public enum Row {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);
    
    private static final int FORWARD_DIRECTION_ROW = 1;
    private static final int BACK_DIRECTION_ROW = -1;
    private static final int STAY_ROW = 0;
    
    private final int row;
    
    Row(int row) {
        this.row = row;
    }
    
    public static Row from(int otherRow) {
        return Arrays.stream(values())
                .filter(row -> row.isSame(otherRow))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Row는 1~8까지의 숫자만 가능합니다. 다시 입력해주세요."));
    }
    
    private boolean isSame(int otherRow) {
        return this.row == otherRow;
    }
    
    public int directionNumberTo(Row otherRow) {
        if (this.row > otherRow.row) {
            return BACK_DIRECTION_ROW;
        }
        
        if (this.row < otherRow.row) {
            return FORWARD_DIRECTION_ROW;
        }
        
        return STAY_ROW;
    }
    
    public int add(int directionNumber) {
        return row + directionNumber;
    }
    
    public int subtract(Row targetRow) {
        return this.row - targetRow.row;
    }
    
    public boolean isLessOrEqualTo(int otherRow) {
        return row <= otherRow;
    }
    
    public boolean isOverOrEqualTo(int otherRow) {
        return row >= otherRow;
    }
    
    public int row() {
        return row;
    }
    
    @Override
    public String toString() {
        return "Row{" +
                "row=" + row +
                '}';
    }
}
