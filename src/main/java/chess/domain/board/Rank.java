package chess.domain.board;

record Rank(int value) {

    private static final int MIN_VALUE_RANGE = 1;
    private static final int MAX_VALUE_RANGE = 8;

    Rank {
        if (value < MIN_VALUE_RANGE || value > MAX_VALUE_RANGE) {
            throw new IllegalArgumentException("유효한 범위의 숫자가 아닙니다.");
        }
    }
}
