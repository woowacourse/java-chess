package chess;

public enum GameState {

    READY, WHITE_RUNNING, BLACK_RUNNING, END;

    public boolean isRunning() {
        return this == WHITE_RUNNING || this == BLACK_RUNNING;
    }

    public GameState getOpposite() {
        if (this == WHITE_RUNNING) {
            return BLACK_RUNNING;
        }
        if (this == BLACK_RUNNING) {
            return WHITE_RUNNING;
        }
        return this;
    }

    public boolean isFinished() {
        return this == END;
    }
}
