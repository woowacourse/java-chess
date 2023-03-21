package chess.command;

import java.util.Arrays;

public enum CommandType {
    START("start"),
    MOVE("move"),
    END("end"),
    ;
    
    private static final String INVALID_COMMAND_ERROR_MESSAGE = "잘못된 명령어입니다.";
    private final String name;
    
    CommandType(final String name) {
        this.name = name;
    }
    
    public static CommandType from(final String command) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        Command.COMMAND_ERROR_PREFIX + INVALID_COMMAND_ERROR_MESSAGE));
    }
}
