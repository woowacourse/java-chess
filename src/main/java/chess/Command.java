package chess;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end"),
    STATUS("status");

    private static final String ERROR_MESSAGE_COMMAND = "[ERROR] 그런 명령어는 없는뎅...ㅎ;;";

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command find(String value) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE_COMMAND));
    }
}
