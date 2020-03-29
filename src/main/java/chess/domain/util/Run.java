package chess.domain.util;

import java.util.Arrays;

public enum Run {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private static final String RUN_NOT_FOUND_EXCEPTION_MESSAGE = "선택할 수 없는 옵션입니다.";

    private final String name;

    Run(String name) {
        this.name = name;
    }

    public static Run of(String name) {
        return Arrays.stream(values())
                .filter(run -> run.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(RUN_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    public boolean isNotEnd() {
        return this != END;
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isStatus() {
        return this == STATUS;
    }
}
