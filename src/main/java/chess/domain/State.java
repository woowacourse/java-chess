package chess.domain;

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
}
