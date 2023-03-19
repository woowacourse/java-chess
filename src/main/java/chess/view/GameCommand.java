package chess.constant;

public enum GameCommand {

    START("start"),
    MOVE("move"),
    END("end");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
