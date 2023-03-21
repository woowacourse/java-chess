package chess.controller;

import java.util.Arrays;
import java.util.List;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command findCommand(List<String> command) {
        return Arrays.stream(Command.values())
                .filter(commands -> commands.command.equals(command.get(0)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("커맨드 일치 x"));
    }

    public boolean isNotEnd() {
        return !this.command.equals("end");
    }
}
