package chess.controller;

import java.util.Arrays;

public enum Command {

    START("start", 1),
    MOVE("move", 3),
    END("end", 1);

    private final String value;
    private final int requiredSize;

    Command(final String value, final int requiredSize) {
        this.value = value;
        this.requiredSize = requiredSize;
    }

    public static Command from(final String rawCommand) {
        return Arrays.stream(Command.values())
            .filter(command -> command.value.equals(rawCommand))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("입력된 명령어가 올바르지 않습니다."));
    }

    public boolean isAppropriateSize(final int size) {
        return size == requiredSize;
    }

    public String getValue() {
        return value;
    }
}
