package domain.command;

public enum Command {

    START,
    MOVE,
    END,
    ;

    public boolean isStart() {
        return this == START;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isEnd() {
        return this == END;
    }
}
