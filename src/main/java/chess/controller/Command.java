package chess.controller;

import java.util.Arrays;

public enum Command {
    START,
    MOVE,
    END,
    EMPTY,
    ;

    public static final int MOVE_COMMAND_SIZE = 3;
    public static final int MOVE_COMMAND_INDEX = 0;
    public static final int MOVE_SOURCE_INDEX = 1;
    public static final int MOVE_TARGET_INDEX = 2;

    public static Command createStart(final String inputCommand) {
        if (START.name().toLowerCase().equalsIgnoreCase(inputCommand)) {
            return START;
        }
        throw new IllegalArgumentException("start를 입력해주세요.");
    }

    public static Command createPlayOrEnd(final String inputCommand) {
        return Arrays.stream(values())
                .filter(command -> command == MOVE || command == END)
                .filter(command -> command.name().toLowerCase().equalsIgnoreCase(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("move 또는 end를 입력해주세요."));
    }
}
