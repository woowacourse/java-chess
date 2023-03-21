package chess.domain;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command parseCommand(String commandHead) {
        return Arrays.stream(Command.values())
                .filter(c -> c.isEqual(commandHead))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바른 명령어가 아닙니다."));
    }

    public boolean isEqual(String other) {
        return command.equals(other);
    }
}
