package chess.controller;

import java.util.Arrays;

public enum Command {
    START,
    MOVE,
    STATUS,
    END,
    EMPTY,
    ;

    public static final int DEFAULT_COMMAND_INDEX = 0;
    public static final int MOVE_SOURCE_INDEX = 1;
    public static final int MOVE_TARGET_INDEX = 2;
    public static final int DEFAULT_COMMAND_SIZE = 1;
    public static final int MOVE_COMMAND_SIZE = 3;
    private static final String INVALID_COMMAND_MESSAGE = "올바른 명령어를 입력해주세요.";

    public static Command from(final String inputCommand) {
        return Arrays.stream(values())
                .filter(command -> command != EMPTY)
                .filter(command -> command.name().equalsIgnoreCase(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE));
    }

    public static void validateCommandSize(final int given, final int expect) {
        if (given != expect) {
            throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
        }
    }
}
