package chess.domain.util;

import java.util.Arrays;

public enum Run {
    START("start"),
    END("end"),
    MOVE("move");

    private final String name;

    Run(String name) {
        this.name = name;
    }

    public static Run of(String name) {
        return Arrays.stream(values())
                .filter(run -> run.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("선택할 수 없는 옵션입니다."));
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
}
