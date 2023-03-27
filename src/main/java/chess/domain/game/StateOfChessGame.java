package chess.domain.game;

public enum StateOfChessGame {

    STARTED(true, false),
    MOVING(true, false),
    PAUSED(true, false),
    FINISHED(true, true);

    private final boolean isStarted;
    private final boolean isFinished;

    StateOfChessGame(final boolean isStarted, final boolean isFinished) {
        this.isStarted = isStarted;
        this.isFinished = isFinished;
    }

    public boolean isSameStateWith(String state) {
        return this.name().equals(state);
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isFinished() {
        return isFinished;
    }

}
