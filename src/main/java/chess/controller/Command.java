package chess.controller;

public enum Command {

    START, END, MOVE, STATUS;

    public boolean isEnd() {
        return this != END;
    }
}
