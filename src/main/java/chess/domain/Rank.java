package chess.domain;

class Rank {

    private static final int MIN_VALUE_RANGE = 1;
    private static final int MAX_VALUE_RANGE = 8;

    private final int value;

    public Rank(int value) {
        if (value < MIN_VALUE_RANGE || value > MAX_VALUE_RANGE) {
            throw new IllegalArgumentException("유효한 범위의 숫자가 아닙니다.");
        }

        this.value = value;
    }
}
