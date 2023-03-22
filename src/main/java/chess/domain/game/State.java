package chess.domain.game;

public enum State {

    RUN,
    START,
    END;

    public boolean isStart() {
        return this == START;
    }

    public boolean isRunnable() {
        return this != END;
    }

    public boolean isCalculable() {
        return this != RUN;
    }
}
