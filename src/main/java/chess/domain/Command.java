package chess.domain;

public enum Command {

    START,
    END,
    MOVE,
    ;

    public boolean isEnd() {
        return this == END;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isStart() {
        return this == START;
    }
}
