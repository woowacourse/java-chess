package chess.domain.position;

import java.util.Objects;

public class RowPosition {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 8;

    private final int rowNumber;

    public RowPosition(int rowNumber) {
        validateNumberRange(rowNumber);
        this.rowNumber = rowNumber;
    }

    private void validateNumberRange(int rowNumber) {
        if (MIN_NUMBER > rowNumber || rowNumber > MAX_NUMBER) {
            throw new IllegalStateException("체스판의 행 번호는 " + rowNumber + "가 될 수 없습니다.");
        }
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
