package controller.constants;

public enum GameState {
    NOT_STARTED,
    RUNNING,
    STOPPED;

    public boolean isNotStarted() {
        return equals(NOT_STARTED);
    }

    public boolean isRunning() {
        return equals(RUNNING);
    }

    public boolean isStopped() {
        return equals(STOPPED);
    }
}
