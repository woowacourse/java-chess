package chess.controller;

public class TryCount {

    private static final int COUNT_UNIT = 1;
    private static final int INITIAL_COUNT = 0;
    private final int maximumTry;
    private int tryCount;

    public TryCount(final int maximumTry) {
        this.maximumTry = maximumTry;
        tryCount = INITIAL_COUNT;
    }

    public boolean canRetry() {
        return maximumTry > tryCount;
    }

    public void resetCount() {
        tryCount = 0;
    }

    public void count() {
        tryCount += COUNT_UNIT;
    }

    public IllegalStateException getException() {
        return new IllegalStateException("입력 시도횟수를 초과하였습니다");
    }
}
