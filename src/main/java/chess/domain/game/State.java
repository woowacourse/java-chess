package chess.domain.game;

public enum State {

    RUN,
    START,
    END,
    CLEAR;

    public boolean isStart() {
        return this == START;
    }

    public boolean isRunnable() {
        return !end();
    }

    private boolean end() {
        return this == END || this == CLEAR;
    }

    public boolean isCalculable() {
        return this != RUN;
    }

    public boolean isClear() {
        return this == CLEAR;
    }
}
