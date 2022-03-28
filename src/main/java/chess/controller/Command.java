package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status")
    ;

    private static final int COMMAND_INDEX = 0;

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command from(String commandText) {
        final String[] splitCommand = commandText.split(" ");
        return Arrays.stream(values())
                .filter(command -> command.value.equals(splitCommand[COMMAND_INDEX]))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }
}
