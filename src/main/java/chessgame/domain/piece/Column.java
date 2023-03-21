package chessgame.domain.piece;

public class Column {
    private static final int MIN_COLUMN = 0;
    private static final int MAX_COLUMN = 7;

    private final int value;

    public Column(int value) {
        this.value = value;
    }

    public static Column from(int value) {
        validate(value);
        return new Column(value);
    }

    private static void validate(int value) {
        if (isOutOfRange(value)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 행의 값입니다.");
        }
    }

    private static boolean isOutOfRange(int value) {
        return value < MIN_COLUMN || value > MAX_COLUMN;
    }

    public int add(Column otherColumn) {
        return this.value + otherColumn.value;
    }

    public int add(int otherColumn) {
        return this.value + otherColumn;
    }

    public int minus(Column otherColumn) {
        return this.value - otherColumn.value;
    }

    public int absoluteOfMinus(Column otherColumn) {
        return Math.abs(this.value - otherColumn.value);
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
}
