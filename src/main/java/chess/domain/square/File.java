package chess.domain.square;

public enum File {

    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    private static final char FIRST_INPUT = 'a';
    private static final String ERROR_OUT_OF_RANGE = "범위 밖의 파일 입니다.";

    public static File from(char input) {
        int index = input - FIRST_INPUT;
        validateRange(index);
        return values()[index];
    }

    private static void validateRange(int value) {
        if (value < 0 || value >= values().length) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    public File add(int value) {
        int sum = ordinal() + value;
        validateRange(sum);
        return values()[sum];
    }

    public int vectorTo(File other) {
        return (int) Math.signum(other.compareTo(this));
    }

    public int distance(File other) {
        return Math.abs(compareTo(other));
    }
}
