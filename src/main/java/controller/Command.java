package controller;

public enum Command {

    START,
    MOVE,
    END,
    ;

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }
}
