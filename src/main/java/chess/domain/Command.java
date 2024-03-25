package chess.domain;

public enum Command {

    START,
    END,
    MOVE,
    ;

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isMove() {
        return this == MOVE;
    }
}
