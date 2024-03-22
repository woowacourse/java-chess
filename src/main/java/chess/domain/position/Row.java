package chess.domain.position;

public enum Row {
    EIGHT,
    SEVEN,
    SIX,
    FIVE,
    FOUR,
    THREE,
    TWO,
    ONE;

    private static final String ERROR_OUT_OF_RANGE = "범위 밖을 벗어난 행 입니다.";

    public static Row from(int value) {
        validateRange(value);
        return values()[value];
    }

    private static void validateRange(int value) {
        if (value < 0 || value >= values().length) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    public Row add(int value) {
        int sum = ordinal() + value;
        validateRange(sum);
        return values()[sum];
    }

    public int vectorTo(Row other) {
        return (int) Math.signum(other.compareTo(this));
    }

    public int distance(Row other) {
        return Math.abs(compareTo(other));
    }
}
