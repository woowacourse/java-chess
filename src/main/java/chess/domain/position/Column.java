package chess.domain.position;

public enum Column {

    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    private static final String ERROR_OUT_OF_RANGE = "범위 밖을 벗어난 열 입니다.";

    public static Column from(int value) {
        validateRange(value);
        return values()[value];
    }

    private static void validateRange(int value) {
        if (value < 0 || value >= values().length) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    public Column add(int value) {
        int sum = ordinal() + value;
        validateRange(sum);
        return values()[sum];
    }

    public int vectorTo(Column other) {
        return (int) Math.signum(other.compareTo(this));
    }

    public int distance(Column other) {
        return Math.abs(compareTo(other));
    }
}
