package piece.coordinate;

public class Row {
    private static final int MIN_ROW = 1;
    private static final int MAX_ROW = 8;
    
    private final int row;
    
    public Row(int row) {
        validateOutOfRange(row);
        this.row = row;
    }
    
    private void validateOutOfRange(int row) {
        if (row < MIN_ROW || row > MAX_ROW) {
            throw new IllegalArgumentException("row는 1~8까지의 숫자만 가능합니다.");
        }
    }
}
