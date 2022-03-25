package chess;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command of(String value) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 명령이 아닙니다."));
    }

    public boolean isStart() {
        return this == START;
    }
}
