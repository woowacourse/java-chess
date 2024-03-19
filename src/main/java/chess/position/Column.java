package chess.position;

public class Column {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 8;

    private final int columnNumber;

    public Column(int columnNumber) {
        validateNumberRange(columnNumber);
        this.columnNumber = columnNumber;
    }

    private void validateNumberRange(int columnNumber) {
        if (MIN_NUMBER > columnNumber || columnNumber > MAX_NUMBER) {
            throw new IllegalStateException("체스판의 열 번호는 " + columnNumber + "가 될 수 없습니다.");
        }
    }
}
