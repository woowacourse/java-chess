package chess.domain;

import java.util.Arrays;

public enum Command {

    START(1),
    MOVE(3),
    END(1);

    private final int requiredSize;

    Command(final int requiredSize) {
        this.requiredSize = requiredSize;
    }

    public static Command from(final String rawCommand) {
        return Arrays.stream(Command.values())
                .filter(command -> command.name().equals(rawCommand.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력된 명령어가 올바르지 않습니다."));
    }

    public boolean isAppropriateSize(final int size) {
        return size == requiredSize;
    }
}
