package view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");


    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command of(final String command) {
        return Arrays.stream(values())
                .filter(value -> command.startsWith(value.value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어 입력입니다."));
    }
}
