package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end"),
    EMPTY(""),
    ;

    public static final int MOVE_COMMAND_SIZE = 3;
    public static final int MOVE_COMMAND_INDEX = 0;
    public static final int MOVE_SOURCE_INDEX = 1;
    public static final int MOVE_TARGET_INDEX = 2;

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command createStart(String inputCommand) {
        if (START.value.equalsIgnoreCase(inputCommand)) {
            return START;
        }
        throw new IllegalArgumentException("START를 입력해주세요.");
    }

    public static Command createPlayOrEnd(String inputCommand) {
        return Arrays.stream(values())
                .filter(command -> command == MOVE || command == END)
                .filter(command -> command.value.equalsIgnoreCase(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("MOVE 또는 END를 입력해주세요."));
    }
}
