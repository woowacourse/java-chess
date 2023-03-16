package chess.controller;

import java.util.Arrays;

public enum Command {
    END("end"),
    PLAY("move"),
    START("start"),
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

    public static Command from(String targetCommand) {
        return Arrays.stream(chess.controller.Command.values())
                .filter(command -> command != EMPTY)
                .filter(command -> command.value.equals(targetCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 커맨드를 입력해주세요."));
    }

    public void validateStartCommand() {
        if (this != START) {
            throw new IllegalArgumentException("START를 입력해주세요.");
        }
    }
}
