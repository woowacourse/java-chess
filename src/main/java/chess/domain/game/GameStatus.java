package chess.domain.game;

public enum GameStatus {

    READY,
    RUNNING,
    TERMINATED;

    public boolean isReady() {
        return this == READY;
    }

    public boolean isRunning() {
        return this == RUNNING;
    }

    public boolean isTerminated() {
        return this == TERMINATED;
    }

    public boolean isNotRunning() {
        return this != RUNNING;
    }
}
