package chess;

public enum GameState {
    READY, WHITE_RUNNING, BLACK_RUNNING, END;

    public boolean isRunning() {
        return this == WHITE_RUNNING || this == BLACK_RUNNING;
    }
}
