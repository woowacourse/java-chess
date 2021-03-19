package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command findCommand(String commandInput) {
        return Arrays.stream(Command.values())
            .filter(command -> command.hasEqualValue(commandInput))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("명령어를 잘못 입력했습니다."));
    }

    private boolean hasEqualValue(String value) {
        return this.value.equals(value);
    }
}
