package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    public static final String INVALID_COMMAND_ERR_MSG = "잘못된 명령이 입력되었습니다.";
    private String command;

    Command(String command) {
        this.command = command;
    }

    public static Command findCommand(String commandMsg) {
        return Arrays.stream(Command.values())
                .filter(command -> command.has(commandMsg))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_ERR_MSG));
    }

    private boolean has(String commandMsg) {
        return this.command.equals(commandMsg);
    }
}
