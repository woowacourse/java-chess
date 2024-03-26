package domain;

public enum GameCommand {
    START, MOVE, STATUS, END;

    public boolean isContinuable() {
        return this != END;
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isStatus() {
        return this == STATUS;
    }

    public boolean isEnd() {
        return this == END;
    }
}
