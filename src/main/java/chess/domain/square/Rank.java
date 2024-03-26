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

    private static final char FIRST_INPUT = '8';
    private static final String ERROR_OUT_OF_RANGE = "범위 밖의 랭크 입니다.";

    public static Rank from(final char input) {
        int index = FIRST_INPUT - input;
        validateRange(index);
        return values()[index];
    }

    private static void validateRange(final int value) {
        if (value < 0 || value >= values().length) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    public Rank add(final int value) {
        int sum = ordinal() + value;
        validateRange(sum);
        return values()[sum];
    }

    public int getVectorTo(final Rank other) {
        return (int) Math.signum(other.compareTo(this));
    }

    public int distanceFrom(final Rank other) {
        return Math.abs(compareTo(other));
    }

    public char toInput() {
        return (char) (FIRST_INPUT - ordinal());
    }
}
