package chess.controller.command;

import java.util.Arrays;

public enum CommandType {

    START(0),
    MOVE(2),
    END(0),
    STATUS(0);

    private final int requiredParameterSize;

    CommandType(final int requiredParameterSize) {
        this.requiredParameterSize = requiredParameterSize;
    }

    public static CommandType from(final String rawCommand) {
        return Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.name().equals(rawCommand.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력된 명령어가 올바르지 않습니다."));
    }

    public boolean isAppropriateSize(final int size) {
        return size == requiredParameterSize;
    }
}
