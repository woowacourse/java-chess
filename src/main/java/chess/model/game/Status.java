package chess.model.game;

public enum Status {
    READY,
    START,
    MOVE,
    END;

    public boolean isReady() {
        return this == READY;
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isRunning() {
        return !isEnd();
    }

    public boolean isEnd() {
        return this == END;
    }
}
