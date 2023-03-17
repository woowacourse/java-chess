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
        return Arrays.stream(Command.values()).filter(c -> c.command.equals(commandHead)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 명령어가 아닙니다."));
    }
}
