package chess;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    Command(String command) {
        this.command = command;
    }
}
