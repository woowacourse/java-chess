package chess.controller;

import java.util.Arrays;

public enum ExecuteState {

    INIT,
    START,
    MOVE,
    END,
    ;

    public static ExecuteState from(final String input) {
        return Arrays.stream(ExecuteState.values())
                .filter(executeState -> executeState != INIT)
                .filter(executeState -> executeState.name().toLowerCase().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 실행 명령입니다."));
    }
}
