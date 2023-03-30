package chess.controller.action;

public class TryCount {

    private static final int COUNT_UNIT = 1;
    private final int maximumTry;
    private int tryCount;

    public TryCount(final int maximumTry) {
        this.maximumTry = maximumTry;
        tryCount = 0;
    }

    public boolean canRetry() {
        return maximumTry > tryCount;
    }

    public void count() {
        tryCount += COUNT_UNIT;
    }

    public IllegalStateException getException() {
        return new IllegalStateException("입력 시도횟수를 초과하였습니다");
    }
}
