package view;

import java.util.Arrays;

public enum CommandShape {
    START("start"),
    END("end"),
    MOVE("move");

    public static final String UNSUPPORTED_COMMAND = "잘못된 명령어 입력입니다.";

    private final String value;

    CommandShape(String value) {
        this.value = value;
    }

    public static CommandShape of(final String command) {
        return Arrays.stream(CommandShape.values())
                .filter(commandShape -> commandShape.value.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNSUPPORTED_COMMAND));
    }
}
