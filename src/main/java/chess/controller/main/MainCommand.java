package chess.controller.main;

import java.util.Arrays;

public enum MainCommand {
    EMPTY,
    USER,
    ROOM,
    START,
    END,
    ;

    private static final String INVALID_COMMAND_MESSAGE = "올바른 명령어를 입력해주세요.";

    public static MainCommand from(final String inputCommand) {
        return Arrays.stream(values())
                .filter(command -> command != EMPTY)
                .filter(command -> command.name().equalsIgnoreCase(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE));
    }
}
