package chess.domain;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command of(String input) {
        return Arrays.stream(values())
                .filter(i -> i.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("start, end, move, status만 입력할 수 있습니다."));
    }

    public boolean isStart() {
        return this == START;
    }
}
