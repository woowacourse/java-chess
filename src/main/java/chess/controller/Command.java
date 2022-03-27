package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status")
    ;

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command from(String commandText) {
        final String[] splitCommand = commandText.split(" ");
        return Arrays.stream(values())
                .filter(command -> command.value.equals(splitCommand[0]))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }
}
