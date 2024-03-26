package domain;

public enum GameCommand {
    START, MOVE, STATUS, SAVE, END;

    public boolean isContinuable() {
        return this != END;
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isStatus() {
        return this == STATUS;
    }

    public boolean isSave() {
        return this == SAVE;
    }

    public boolean isEnd() {
        return this == END;
    }
}
