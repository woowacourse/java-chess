package chess.constant;

public enum GameCommand {
    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
