package chess.domain;

public enum Command {

    START("start"),
    MOVE("move"),
    END("end");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command from(String input) {
        return Command.valueOf(input.toUpperCase());
    }
}

