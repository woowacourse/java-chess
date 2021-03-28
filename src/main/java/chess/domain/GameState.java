package chess.domain;

public enum GameState {
    NOT_STARTED,
    START,
    END;

    public boolean isEnd() {
        return this == END;
    }

    public boolean isStart() {
        return this == START;
    }
}
