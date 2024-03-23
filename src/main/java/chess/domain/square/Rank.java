package chess.domain.square;

public enum Rank {
    EIGHT,
    SEVEN,
    SIX,
    FIVE,
    FOUR,
    THREE,
    TWO,
    ONE;

    private static final String ERROR_OUT_OF_RANGE = "범위 밖의 랭크 입니다.";

    public static Rank from(int value) {
        validateRange(value);
        return values()[value];
    }

    private static void validateRange(int value) {
        if (value < 0 || value >= values().length) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    public Rank add(int value) {
        int sum = ordinal() + value;
        validateRange(sum);
        return values()[sum];
    }

    public int vectorTo(Rank other) {
        return (int) Math.signum(other.compareTo(this));
    }

    public int distance(Rank other) {
        return Math.abs(compareTo(other));
    }
}
