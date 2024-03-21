package chess.domain.position;

import java.util.Objects;

public class ColumnPosition {
    public static final int MIN_NUMBER = 0;
    public static final int MAX_NUMBER = 7;

    private final int columnNumber;

    public ColumnPosition(int columnNumber) {
        validateNumberRange(columnNumber);
        this.columnNumber = columnNumber;
    }

    //TODO: 더 나은 메서드 명 고민해보기
    public int move(int columnMove) {
        return columnNumber + columnMove;
    }

    public int intervalWith(ColumnPosition otherColumnPosition) {
        return Math.abs(columnNumber - otherColumnPosition.columnNumber);
    }

    //TODO: 더 나은 메서드 명 고민해보기
    public boolean isLeft(ColumnPosition otherColumnPosition) {
        return columnNumber < otherColumnPosition.columnNumber;
    }

    //TODO: 더 나은 메서드 명 고민해보기
    public boolean isRight(ColumnPosition otherColumnPosition) {
        return columnNumber > otherColumnPosition.columnNumber;
    }

    private void validateNumberRange(int columnNumber) {
        if (MIN_NUMBER > columnNumber || columnNumber > MAX_NUMBER) {
            throw new IllegalStateException("체스판의 열 번호는 " + columnNumber + "가 될 수 없습니다.");
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
        ColumnPosition that = (ColumnPosition) o;
        return columnNumber == that.columnNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnNumber);
    }
}
