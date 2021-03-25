package chess.view;

import java.util.Arrays;

public enum Command {
    START("START", true),
    END("END", false),
    MOVE("MOVE", true),
    STATUS("STATUS", false);

    private final String command;
    private final boolean isPrint;

    Command(final String command, final boolean isPrint) {
        this.command = command;
        this.isPrint = isPrint;
    }

    public static boolean isValidateCommand(final String command) {
        return Arrays.stream(Command.values())
                .anyMatch(cmd -> cmd.command.equals(command));
    }

    public boolean isPrint() {
        return this.isPrint;
    }
}
