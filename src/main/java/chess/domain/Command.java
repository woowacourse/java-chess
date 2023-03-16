package chess.domain;

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
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 명령어입니다."));
    }

    public boolean isAppropriateSize(final int size) {
        return size == requiredSize;

    }


}
