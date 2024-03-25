package domain.game;

public enum GameState {
    READY,
    RUNNING,
    END;

    public boolean isRunning() {
        return this == RUNNING;
    }

    public boolean isNotRunning() {
        return !isRunning();
    }
}
