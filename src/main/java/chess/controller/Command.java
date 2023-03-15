package chess.controller;

import java.util.Arrays;

public enum Command {
    START,
    END,
    MOVE;

    private static final String COMMAND_ERROR_MESSAGE = "잘못된 명령어 입력입니다. (start, end, move)";

    public static Command of(final String command) {
        return Arrays.stream(values())
                .filter(e -> e.name().equalsIgnoreCase(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_ERROR_MESSAGE));
    }
}
