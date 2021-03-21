package chess.view;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end");

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public boolean isSame(String input) {
        return command.equalsIgnoreCase(input);
    }
}
