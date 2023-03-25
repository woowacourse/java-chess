package chess.domain.game;

public enum StateOfChessGame {
    READY(false, false),
    RUNNING(true, false),
    FINISHED(true, true);

    private final boolean isStarted;
    private final boolean isFinished;

    StateOfChessGame(final boolean isStarted, final boolean isFinished) {
        this.isStarted = isStarted;
        this.isFinished = isFinished;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
