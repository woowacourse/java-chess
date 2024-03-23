package chess.domain;

public enum CommandType {
    START("start"),
    MOVE("move"),
    END("end");

    private final String commandType;

    CommandType(String commandType) {
        this.commandType = commandType;
    }

    public boolean sameCommand(String command) {
        return this.commandType.equals(command);
    }
}
