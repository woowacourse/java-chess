package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String rawCommand;

    Command(String rawCommand) {
        this.rawCommand = rawCommand;
    }

    public static boolean hasCommand(String input) {
        return Arrays.stream(Command.values())
                .anyMatch(command -> command.sameWith(input));
    }

    public boolean sameWith(String input) {
        return rawCommand.equals(input);
    }

    public boolean startsWith(String input) {
        return input.startsWith(rawCommand);
    }

    public String command() {
        return rawCommand;
    }
}
