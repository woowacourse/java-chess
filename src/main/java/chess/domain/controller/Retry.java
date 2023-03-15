package chess.domain.controller;

public final class Retry {

    public static final String RETRY_FAIL_MESSAGE = "재입력 횟수를 초과하여 게임을 종료합니다.";
    public static final String EXCEPTION_MESSAGE = "재입력 횟수를 초과하였습니다.";

    private final int count;

    public Retry(final int count) {
        this.count = count;
    }

    public boolean isRepeatable() {
        return count > 0;
    }

    public Retry decrease() {
        return new Retry(count - 1);
    }
}

