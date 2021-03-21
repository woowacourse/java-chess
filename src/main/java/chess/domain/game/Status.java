package chess.domain.game;

public enum Status {
    STOP, RUNNING;
    
    public boolean isRunning() {
        return this == RUNNING;
    }
}
