package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end"),
    STATUS("status");

    private static final String INVALID_COMMAND_EXCEPTION = "유효하지 않은 명령입니다.";

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command from(String text) {
        return Arrays.stream(values())
                .filter(command -> text.startsWith(command.value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_EXCEPTION));
    }
}
