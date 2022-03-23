package chess.domain;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum ChessExecution {
    END("end"),
    ;

    private static final String NO_COMMAND_FIND = "게임 실행중 명령어는 end만 입력할 수 있습니다.";

    private final String value;

    ChessExecution(String value) {
        this.value = value;
    }

    public static ChessExecution from(String value) {
        return Arrays.stream(values())
            .filter(execution -> execution.value.equalsIgnoreCase(value))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException(NO_COMMAND_FIND));
    }
}
