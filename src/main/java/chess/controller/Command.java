package chess.controller;

import java.util.Arrays;
import java.util.List;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private static final int COMMAND_INDEX = 0;
    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command getCommand(final List<String> command) {
        return Arrays.stream(Command.values())
                .filter(commands -> commands.command.equals(command.get(COMMAND_INDEX)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 커맨드입니다."));
    }

    public boolean isNotEnd() {
        return this != END;
    }

}
