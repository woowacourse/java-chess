package chess.controller;

import java.util.Arrays;
import java.util.List;

public enum GameStatus {
    INIT,
    START,
    MOVE,
    END;

    public static final String INVALID_COMMAND_MESSAGE = "잘못된 커맨드를 입력하였습니다.";
    private static final int DEFAULT_COMMAND_INDEX = 0;

    public static boolean isStart(final String input) {
        return START.name().equalsIgnoreCase(input);
    }

    public static boolean isMove(final String input) {
        return MOVE.name().equalsIgnoreCase(input);
    }

    public static boolean isEnd(final String input) {
        return END.name().equalsIgnoreCase(input);
    }

    public static GameStatus from(final List<String> input) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(input.get(DEFAULT_COMMAND_INDEX)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE));
    }

    public boolean isEnd() {
        return this == END;
    }
}
