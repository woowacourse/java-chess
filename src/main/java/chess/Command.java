package chess;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private static final String NOT_EXIST_COMMAND_EXCEPTION_MESSAGE = "존재하지 않는 명령어입니다.";
    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command of(String command) {
        return Arrays.stream(values())
            .filter(value -> value.command.toLowerCase().equals(command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_COMMAND_EXCEPTION_MESSAGE));
    }
}
