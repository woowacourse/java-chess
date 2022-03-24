package chess;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move");

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command of(final String value) {
        return Arrays.stream(values())
                .filter(command -> command.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다."));
    }
}
