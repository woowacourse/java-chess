package chess.controller;

import java.util.Arrays;

enum Command {
    START("start"),
    MOVE("move"),
    END("end"),
    ;

    public static final int MOVE_COMMAND_INDEX = 0;
    public static final int MOVE_SOURCE_INDEX = 1;
    public static final int MOVE_TARGET_INDEX = 2;

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command getValidate(
            final String inputCommand, final Command firstCommand, final Command secondCommand
    ) {
        return Arrays.stream(values())
                .filter(command -> command == firstCommand || command == secondCommand)
                .filter(command -> command.value.equals(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        firstCommand.value + " 또는 " + secondCommand.value + " 를 입력해주세요."));
    }
}
