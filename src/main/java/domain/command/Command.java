package domain.command;

public enum Command {

    START,
    MOVE,
    END,
    ;

    public boolean isNotStart() {
        return this != START;
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isMove() {
        return this == START;
    }
}
