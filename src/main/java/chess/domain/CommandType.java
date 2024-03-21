package chess.domain;

public enum CommandType {
    START("start"),
    MOVE("move"),
    END("end");

    private final String commandType;

    CommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandType() {
        return commandType;
    }
}
