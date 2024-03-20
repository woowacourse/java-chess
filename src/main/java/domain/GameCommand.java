package domain;

public enum GameCommand {
    START, MOVE, END;

    public boolean isContinuable() {
        return this != END;
    }
}
