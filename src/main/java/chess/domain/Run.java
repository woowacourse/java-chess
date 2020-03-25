package chess.domain;

import java.util.Arrays;

public enum Run {
    START("start"),
    END("end");

    private final String value;

    Run(String value) {
        this.value = value;
    }

    public static Run of(String value) {
        return Arrays.stream(values())
                .filter(run -> run.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 run을 찾을 수 없습니다."));
    }

    public boolean isStart() {
        return this == START;
    }
}
