package chess.controller.command;

import java.util.Arrays;

public enum CommandType {

    START(1),
    MOVE(3),
    END(1),
    STATUS(1);

    private final int requiredSize;

    CommandType(final int requiredSize) {
        this.requiredSize = requiredSize;
    }

    public static CommandType from(final String rawCommand) {
        return Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.name().equals(rawCommand.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력된 명령어가 올바르지 않습니다."));
    }

    public boolean isNotAppropriateSize(final int size) {
        return size != requiredSize;
    }
}
