package chessgame.domain.piece;

import java.util.Objects;

public class Row {

    private static final int MIN_ROW = 0;
    private static final int MAX_ROW = 7;

    private final int value;

    public Row(int value) {
        this.value = value;
    }

    public static Row from(int value) {
        validate(value);
        return new Row(value);
    }

    public static Row fromWithoutValidate(int value) {
        return new Row(value);
    }

    private static void validate(int value) {
        if (isOutOfRange(value)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 행의 값입니다.");
        }
    }

    private static boolean isOutOfRange(int value) {
        return value < MIN_ROW || value > MAX_ROW;
    }

    public int add(Row otherRow) {
        return this.value + otherRow.value;
    }

    public int add(int otherRow) {
        return this.value + otherRow;
    }

    public int minus(Row otherRow) {
        return this.value - otherRow.value;
    }

    public int absoluteOfMinus(Row otherRow) {
        return Math.abs(this.value - otherRow.value);
    }

    public boolean isPositive() {
        return this.value > 0;
    }

    public boolean isNegative() {
        return this.value < 0;
    }

    public boolean isZero() {
        return this.value == 0;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return value == row.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
