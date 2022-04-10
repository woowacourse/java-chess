package chess.command;

import java.util.Arrays;

public enum CommandType {
    START("start"),
    MOVE("move"),
    END("end"),
    STATUS("status"),
    CONTINUE("continue");

    private static final String ERROR_MESSAGE_COMMAND = "[ERROR] 그런 명령어는 없는뎅...ㅎ;;";

    private final String name;

    CommandType(String name) {
        this.name = name;
    }

    public static CommandType from(String name) {
        return Arrays.stream(CommandType.values())
            .filter(commandType -> commandType.name.equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE_COMMAND));
    }
}
