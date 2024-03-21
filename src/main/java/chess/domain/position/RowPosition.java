package chess.domain.position;

import java.util.Objects;

public class RowPosition {
    public static final RowPosition ONE = new RowPosition(1);
    public static final RowPosition SIX = new RowPosition(6);
    public static final int MIN_NUMBER = 0;
    public static final int MAX_NUMBER = 7;

    private final int rowNumber;

    public RowPosition(int rowNumber) {
        validateNumberRange(rowNumber);
        this.rowNumber = rowNumber;
    }

    public int move(int rowMove) {
        return rowNumber + rowMove;
    }

    public RowPosition reverse() {
        return new RowPosition(MAX_NUMBER - rowNumber);
    }

    public int intervalWith(RowPosition otherRowPosition) {
        return Math.abs(rowNumber - otherRowPosition.rowNumber);
    }

    public boolean isHigherThan(RowPosition target) {
        return rowNumber > target.rowNumber;
    }

    public boolean isLowerThan(RowPosition target) {
        return rowNumber < target.rowNumber;
    }

    private void validateNumberRange(int rowNumber) {
        if (MIN_NUMBER > rowNumber || rowNumber > MAX_NUMBER) {
            throw new IllegalStateException("체스판의 행 번호는 " + rowNumber + "가 될 수 없습니다.");
        }
    }

    public int getRowNumber() {
        return rowNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RowPosition that = (RowPosition) o;
        return rowNumber == that.rowNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowNumber);
    }
}
