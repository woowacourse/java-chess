package chess.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum CommandType {
    START("start", 1),
    STATUS("status", 1),
    MOVE("move", 3),
    END("end", 1),
    EMPTY("_empty", 0),
    ;

    private final String code;
    private final int size;

    CommandType(final String code, final int size) {
        this.code = code;
        this.size = size;
    }

    public static CommandType from(final List<String> commandWithOptions) {
        return Arrays.stream(values())
                     .filter(commandType -> isCommandOf(commandType, commandWithOptions))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 입력입니다"));
    }

    private static boolean isCommandOf(final CommandType commandType, final List<String> commandWithOptions) {
        final String commandCode = commandWithOptions.get(Command.COMMAND_INDEX);

        return Objects.equals(commandCode, commandType.code) && commandType.size == commandWithOptions.size();
    }
}
