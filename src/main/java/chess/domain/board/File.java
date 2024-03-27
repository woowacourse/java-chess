package chess.domain.board;

record File(char value) {

    private static final char MIN_VALUE_RANGE = 'a';
    private static final char MAX_VALUE_RANGE = 'h';

    File {
        if (value < MIN_VALUE_RANGE || value > MAX_VALUE_RANGE) {
            throw new IllegalArgumentException("유효한 범위의 알파벳이 아닙니다.");
        }
    }
}
