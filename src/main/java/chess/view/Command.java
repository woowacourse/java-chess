package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static boolean isValidateCommand(final String command) {
        return Arrays.stream(Command.values())
                .anyMatch(cmd -> cmd.command.equals(command));
    }
}
