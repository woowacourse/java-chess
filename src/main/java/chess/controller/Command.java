package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    STATUS("status"),
    MOVE("move"),
    END("end");

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

    public boolean isStatus() {
        return this == STATUS;
    }

    public boolean isMove() {
        return this == MOVE;
    }
}
