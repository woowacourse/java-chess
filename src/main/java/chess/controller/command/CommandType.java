package chess.controller.command;

public enum CommandType {

    CREATE, LOAD, START, END, MOVE, STATUS;

    public boolean isEnd() {
        return this == END;
    }
}
