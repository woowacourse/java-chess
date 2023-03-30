package chess.controller.command;

import java.util.List;

public enum CommandType {

    CREATE, LOAD, WAIT, START, END, MOVE, STATUS;

    public List<CommandType> getNextPossibleCommandTypes(boolean isGameEnd) {
        if (this == CREATE || this == LOAD) {
            return List.of(START, END, MOVE, STATUS);
        }
        if (this == WAIT) {
            return List.of(START, END);
        }
        if (!isGameEnd) {
            return List.of(END, MOVE, STATUS);
        }
        return List.of(END);
    }
}
