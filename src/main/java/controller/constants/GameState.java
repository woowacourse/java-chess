package controller.constants;

public enum GameState {
    RUNNING,
    STOPPED;

    public boolean isRunning() {
        return equals(RUNNING);
    }

    public boolean isStopped() {
        return equals(STOPPED);
    }
}
